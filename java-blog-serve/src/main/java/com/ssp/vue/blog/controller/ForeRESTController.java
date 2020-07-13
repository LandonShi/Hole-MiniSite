package com.ssp.vue.blog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.vo.CountVO;
import com.ssp.vue.blog.common.dto.LoginDTO;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.common.vo.PersonValueVO;
import com.ssp.vue.blog.common.vo.WebSiteVO;
import com.ssp.vue.blog.entity.*;
import com.ssp.vue.blog.service.*;
import com.ssp.vue.blog.common.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ForeRESTController {
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    UserService userService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    LogService logService;
    @Autowired
    BlogService blogService;
    @Autowired
    TagService tagService;
    @Autowired
    TypeService typeService;
    @Autowired
    LinkService linkService;
    @Autowired
    AtlasService atlasService;
    @Autowired
    PhotoService photoService;
    @Autowired
    WebcomService webcomService;
    @Autowired
    WebrepService webrepService;
    @Autowired
    CommentService commentService;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO, HttpServletResponse response, HttpServletRequest request) {
        //校验信息
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDTO.getUsername()));
        Assert.notNull(user, "用户不存在");
        if(!user.getPassword().equals(SecureUtil.md5(loginDTO.getPassword()))) {
            return Result.fail("密码错误！");
        }
        //校验成功
        String jwt = jwtUtil.generateToken(user.getId());
        //保存用户日志信息
        saveUserLog(request);
        //将用户信息已JSON形式存入redis，方便后继续业务获取用户信息
        JSONObject user_json = (JSONObject) JSONObject.toJSON(user);
        redisUtil.set(jwt, user_json);
        //返回客户端token 1
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        // 或者从这里拿到用户信息 2
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("nickname", user.getNickname())
                .put("avatar", user.getAvatar())
                .map()
        );
    }

    /*
    保存用户登录日志
     */
    private void saveUserLog(HttpServletRequest request) {
        Log log = new Log();
        log.setLoginDate(LogUtil.getDate());
        log.setTypeDevice(LogUtil.getDeviceType(request));
        log.setAddressIP(LogUtil.getIpAddress(request));
        log.setLogoutDate(null);
        //登出时间应该在用户关闭当前页面 和用户点击退出按钮时记录下来
        logService.save(log);
    }

    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        System.out.println("---------------执行用户注销-------------------------");
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }

    /*
    主页获取 三个类型数目与外链
     */
    @GetMapping("/count")
    public Result getTotalCount() {
        CountVO countVO = new CountVO();
        Integer blogCount = blogService.count();
        Integer tagCount = tagService.count();
        Integer typeCount = typeService.count();
        List<Link> linkList = linkService.list();
        countVO.setBlogCount(blogCount);
        countVO.setTagCount(tagCount);
        countVO.setTypeCount(typeCount);
        countVO.setLinks(linkList);
        return Result.succ(countVO);
    }

    /*
    ++ views
     */
    @PostMapping("/views/{bid}")
    public Result addViews(@PathVariable("bid") Integer bid) {
        Blog blog = blogService.getById(bid);
        Integer views = blog.getViews();
        views++;
        blog.setViews(views);
        blogService.saveOrUpdate(blog);
        return Result.succ("+1");
    }

    /*
    前台获取已发布文章
     */
    @GetMapping("/blogs/publish")
    public Result getAllBlogsAndPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        List<Blog> blogList = blogService.list(new QueryWrapper<Blog>().eq("statue", 1).eq("recommend", true).orderByDesc("id"));
        for(Blog blog: blogList) {
            blog.setType(typeService.getById(blog.getTid()));
            blog.setUser(userService.getById(1));
        }
        IPage<Blog> page = new Page<>(currentPage, 10);
        page.setRecords(blogList);
        //总记录数
        page.setTotal(blogList.size());
        //当前分页位置
        page.setCurrent(currentPage);
        return Result.succ(page);
    }

    @GetMapping("/blogs_type/{tid}")
    public Result getBlogByType(@PathVariable("tid") Integer tid) {
        Type type = typeService.getById(tid);
        List<Blog> blogList = blogService.list(new QueryWrapper<Blog>().eq("tid", tid));
        type.setBlogList(blogList);
        return Result.succ(type);
    }

    @GetMapping("/bless/{tagID}")
    public Result getBlogByTag(@PathVariable("tagID") Integer tagID) {
        Tag tag = tagService.getById(tagID);
        List<Integer> blogsID = tagService.listTagBlogs(tagID);
        List<Blog> blogs = new ArrayList<>();
        for(Integer bid : blogsID) {
            Blog blog = blogService.getById(bid);
            blogs.add(blog);
        }
        tag.setBlogList(blogs);
        return Result.succ(tag);
    }

    @GetMapping("/tags/fore")
    public Object listAndPage() {
        //拿到所有的标签
        List<Tag> tagList = tagService.list(new QueryWrapper<Tag>().orderByDesc("id"));
        for(Tag tag: tagList) {
            //根据标签id查询中间表，找出这个标签存在于哪些文章
            List<Integer> bids = tagService.listTagBlogs(tag.getId());
            List<Blog> blogs = new ArrayList<>();
            if(bids == null)
                tag.setBlogList(null);
            else {
                for (Integer bid : bids) {
                    //拿到这个文章对象
                    Blog blog = blogService.getById(bid);
                    if (blog != null)
                        //放入集合
                        blogs.add(blog);
                }
                tag.setBlogList(blogs);
            }
        }
        return Result.succ(tagList);
    }

    @GetMapping("/atlas/recently")
    public Result getOne() {
        Atlas atlas = atlasService.getOneRec();
        List<Photo> photos = photoService.list(new QueryWrapper<Photo>().eq("atlas_id", atlas.getId()));
        atlas.setPhotos(photos);
        return Result.succ(atlas);
    }

    @GetMapping("/atlas/all")
    public Result getAtlasAll(@RequestParam(defaultValue = "1") Integer currentPage) {
        List<Atlas> atlases = atlasService.list(new QueryWrapper<Atlas>().eq("statue", true));
        for(Atlas atlas: atlases) {
            List<Photo> photos = photoService.list(new QueryWrapper<Photo>().eq("atlas_id", atlas.getId()));
            atlas.setPhotos(photos);
        }
        IPage<Atlas> page = new Page<>(currentPage, 10);
        //提供数据源
        page.setRecords(atlases);
        //总记录数
        page.setTotal(atlases.size());
        //当前分页位置
        page.setCurrent(currentPage);
        return Result.succ(atlases);
    }

    @GetMapping("/blogs/command")
    public Result getAllBlogs(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page<Blog> page = new Page<>(currentPage, 10);
        IPage<Blog> pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("id"));
        return Result.succ(pageData);
    }

    @GetMapping("/webcomes/rep")
    public Result listRep(@RequestParam(defaultValue = "1") Integer currentPage) {
        List<Webcom> webcoms = webcomService.list(new QueryWrapper<Webcom>().orderByDesc("id"));
        for(Webcom webcom: webcoms) {
            List<Webrep> webrepList = webrepService.list(new QueryWrapper<Webrep>().eq("com_id", webcom.getId()));
            webcom.setWebreps(webrepList);
        }
        IPage<Webcom> page = new Page<>(currentPage, 10);
        //提供数据源
        page.setRecords(webcoms);
        //总记录数
        page.setTotal(webcoms.size());
        //当前分页位置
        page.setCurrent(currentPage);
        return Result.succ(page);
    }

    @GetMapping("/webcomes/unrep")
    public Result getUnRepList() {
        List<Webcom> webcomes = webcomService.list(new QueryWrapper<Webcom>().eq("statue",false));
        List<Comment> comments = commentService.list(new QueryWrapper<Comment>().eq("statue", false));
        PersonValueVO personValueVO = new PersonValueVO();
        personValueVO.setWebcomes(webcomes);
        personValueVO.setComments(comments);
        return Result.succ(personValueVO);
    }

    @GetMapping("/website")
    public Result getWebSiteInfo() {
        List<Type> types = typeService.list();
        List<Tag> tags = tagService.list();
        List<Blog> blogs = blogService.list();
        List<Link> links = linkService.list();
        List<Webcom> webcoms = webcomService.list();
        List<Comment> comments = commentService.list();
        WebSiteVO webSiteVO = new WebSiteVO();
        webSiteVO.setTypes(types);
        webSiteVO.setTags(tags);
        webSiteVO.setBlogs(blogs);
        webSiteVO.setLinks(links);
        webSiteVO.setWebcomes(webcoms);
        webSiteVO.setComments(comments);
        return Result.succ(webSiteVO);
    }

}

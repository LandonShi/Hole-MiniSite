package com.ssp.vue.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.common.util.ImageUtil;
import com.ssp.vue.blog.entity.*;
import com.ssp.vue.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class BlogController {

    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @Autowired
    CommentService commentService;
    @Autowired
    ReplyService replyService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    @PostMapping("/blogs")
    public Result sendBlog(@RequestBody Blog blog) {
        Integer id = blog.getId();
        if(id != null) {
            blog.setUpdateDate(LocalDateTime.now());
            blogService.updateById(blog);
        } else {
            User user = userService.getById(1);   //暂且用这种方式拿到User
            blog.setCreateDate(LocalDateTime.now());  //创建时间
            blog.setUpdateDate(null);  //更新时间
            blog.setUid(user.getId());  //用户
            blog.setTid(blog.getTid());  //类型
            blog.setRecommend(false);  //不推荐
            blog.setViews(0);  //浏览量
            blogService.saveBlog(blog);  //保存并返回主键存入blog
            //处理标签
            List<Integer> tagsID = blog.getTags();
            for(Integer tagID: tagsID) {
                tagService.saveTagAndBlog(blog.getId(), tagID);
            }
        }
        return Result.succ("操作成功");
    }

    @PostMapping("/blogs/upload")
    public Result postImage(HttpServletRequest req, MultipartFile image) {
        StringBuffer url = new StringBuffer();
        String filePath = req.getServletContext().getRealPath("blog/" + sdf.format(new Date()));
        File imageFolder = new File(filePath);
        String fileName = image.getOriginalFilename();
        assert fileName != null;
        //这种方式获取到上传文件名比较取巧，因为存在用户上传文件名本身带了.
        //但考虑到是个人网站，没太多了的约束
        String[] str = fileName.split("\\.");
        File file = new File(imageFolder,sdf.format(new Date()) + str[0] +  ".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            //做格式转换
            BufferedImage img = ImageUtil.change2jpg(file);
            assert img != null;
            ImageIO.write(img, "jpg", file);
            //保存完图片之后，需要将地址返回
            url.append(req.getScheme())
                    .append("://")
                    .append(req.getServerName())
                    .append(":")
                    .append(req.getServerPort())
                    .append(req.getContextPath())
                    .append(filePath);
            return Result.succ(200,"success", url.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.fail("error", "上传失败");
    }

    @GetMapping("/blogs")
    public Result getAllBlogsAndPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        List<Blog> blogList = blogService.list(new QueryWrapper<Blog>().orderByDesc("id"));
        for(Blog blog: blogList)
            blog.setType(typeService.getById(blog.getTid()));
        IPage<Blog> page = new Page<>(currentPage, 10);
        //提供数据源
        page.setRecords(blogList);
        //总记录数
        page.setTotal(blogList.size());
        //当前分页位置
        page.setCurrent(currentPage);
        return Result.succ(page);
    }

    @GetMapping("/blogs/{bid}")
    public Result getBlog(@PathVariable("bid") Long bid) {
        Blog blog = blogService.getById(bid);
        blog.setType(typeService.getById(blog.getTid()));
        blog.setTags(tagService.findTagsByBlog(bid));
        blog.setUser(userService.getById(1));
        List<Comment> commentList = commentService.list(new QueryWrapper<Comment>().eq("bid", bid).orderByDesc("id"));
        for (Comment comment : commentList)
            comment.setReplies(replyService.list(new QueryWrapper<Reply>().eq("cid", comment.getId()).orderByDesc("id")));

        blog.setComments(commentList);
        return Result.succ(blog);
    }

    @GetMapping("blogs/statue")
    public Result getStatueBlog(@RequestParam("statue") Integer statue, @RequestParam("currentPage") Integer currentPage) {
        List<Blog> blogList = blogService.list(new QueryWrapper<Blog>().eq("statue", statue).orderByDesc("id"));
        for(Blog blog: blogList) {
            blog.setType(typeService.getById(blog.getTid()));
        }
        IPage<Blog> page = new Page<>(currentPage, 10);
        page.setRecords(blogList);
        //总记录数
        page.setTotal(blogList.size());
        //当前分页位置
        page.setCurrent(1);
        return Result.succ(page);
    }

    /*
    下架文章到回收站
     */
    @PutMapping("/blogs")
    public Result deleteBlog(@RequestParam("bid") Integer bid) {
        Blog blog = blogService.getById(bid);
        blog.setStatue(-1);
        blogService.updateById(blog);
        return Result.succ("操作成功");
    }

    /*
    回收站恢复为草稿
     */
    @PutMapping("/blogs/recover")
    public Result recoverBlog(@RequestParam("bid") Integer bid) {
        Blog blog = blogService.getById(bid);
        blog.setStatue(0);
        blogService.updateById(blog);
        return Result.succ("操作成功");
    }

    /*
    回收站直接发布
     */
    @PutMapping("/blogs/publish")
    public Result publishBlog(@RequestParam("bid") Integer bid) {
        Blog blog = blogService.getById(bid);
        blog.setStatue(1);
        blogService.updateById(blog);
        return Result.succ("操作成功");
    }

    /*
    推荐文章
     */
    @PutMapping("/blogs/recommend")
    public Result recommendBlog(@RequestParam("bid") Integer bid) {
        Blog blog = blogService.getById(bid);
        blog.setRecommend(true);
        blogService.updateById(blog);
        return Result.succ("操作成功");
    }

    /*
    回收站删除文章
     */
    @DeleteMapping("/blogs/delete")
    public Result delete(@RequestParam("bid") Integer bid) {
        //删除与博客相关的标签
        tagService.removeTagByBlog(bid);
        //删除博客
        blogService.removeById(bid);
        return Result.succ("操作成功");
    }

    /*
    批量删除
     */
    @DeleteMapping("/blog_set")
    public Object deleteSelBatchBlog(@RequestParam("arr") String arr) {
        String[] aars = arr.split(",");
        for (String aar : aars) {
            Integer blog_id = Integer.valueOf(aar);
            tagService.removeTagByBlog(blog_id);
            blogService.removeById(blog_id);
        }
        return Result.succ("操作成功");
    }

}

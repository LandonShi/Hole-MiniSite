package com.ssp.vue.blog.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Blog;
import com.ssp.vue.blog.entity.Tag;
import com.ssp.vue.blog.service.BlogService;
import com.ssp.vue.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TagController {

    @Autowired
    TagService tagService;
    @Autowired
    BlogService blogService;

    @GetMapping("/tags")
    public Object listAndPage(@RequestParam("currentPage") Integer currentPage,
                              @RequestParam("pageSize") Integer pageSize) {
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
        IPage<Tag> page = new Page<>(currentPage, pageSize);
        //提供分页数据源
        page.setRecords(tagList);
        //总记录数
        page.setTotal(tagList.size());
        return Result.succ(page);
    }

    @GetMapping("/tags/list")
    public Object listTag() {
        List<Tag> tags = tagService.list();
        return Result.succ(tags);
    }

    @GetMapping("/tags/{bid}")
    public Result listTags(@PathVariable("bid") Long bid) {
        List<Integer> tagList = tagService.findTagsByBlog(bid);
        return Result.succ(tagList);
    }

    @PostMapping("/tags")
    public Object addType(@RequestBody Tag tag) {
        tag.setCreateDate(LocalDateTime.now());
        tagService.save(tag);
        return Result.succ("新增成功");
    }

    @DeleteMapping("/tags")
    public Object deleteAtlas(@RequestParam("tid") Integer tid) {
        tagService.removeById(tid);
        return Result.succ("删除成功");
    }

    @DeleteMapping("/tagged")
    public Object deleteSelBatchAtlas(@RequestParam("arr") String arr) {
        String[] aars = arr.split(",");
        for (String aar : aars) {
            Integer tag_id = Integer.valueOf(aar);
            tagService.removeById(tag_id);
        }
        return Result.succ("操作成功");
    }
}

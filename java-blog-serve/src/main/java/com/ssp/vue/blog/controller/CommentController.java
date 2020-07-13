package com.ssp.vue.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Comment;
import com.ssp.vue.blog.entity.Reply;
import com.ssp.vue.blog.service.BlogService;
import com.ssp.vue.blog.service.CommentService;
import com.ssp.vue.blog.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @Autowired
    ReplyService replyService;

    @PostMapping("/comments")
    public Result add(@RequestBody Comment comment) {
        comment.setCreateDate(LocalDateTime.now());
        comment.setStatue(false);
        commentService.save(comment);
        return Result.succ("评论成功，站长正在快马加鞭赶过来！");
    }

    @GetMapping("/comments")
    public Result listAndPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        List<Comment> commentList = commentService.list(new QueryWrapper<Comment>().orderByDesc("id"));
        for(Comment comment: commentList) {
            comment.setBlog(blogService.getById(comment.getBid()));
        }
        IPage<Comment> page = new Page<>(currentPage, 10);
        page.setRecords(commentList);
        //总记录数
        page.setTotal(commentList.size());
        //当前分页位置
        page.setCurrent(1);
        return Result.succ(page);
    }

    @GetMapping("/comments/statue")
    public Result listStatueAndPage(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam Integer statue) {
        List<Comment> commentList = commentService.list(new QueryWrapper<Comment>().eq("statue", statue).orderByDesc("id"));
        for(Comment comment: commentList)
            comment.setBlog(blogService.getById(comment.getBid()));
        IPage<Comment> page = new Page<>(currentPage, 10);
        page.setRecords(commentList);
        //总记录数
        page.setTotal(commentList.size());
        //当前分页位置
        page.setCurrent(1);
        return Result.succ(page);
    }

    @GetMapping("/comments/{cid}")
    public Result get(@PathVariable("cid") Integer cid) {
        Comment comment = commentService.getById(cid);
        List<Reply> replies = replyService.list(new QueryWrapper<Reply>().eq("cid", comment.getId()));
        comment.setReplies(replies);
        return Result.succ(comment);
    }

    @DeleteMapping("/comments/{cid}")
    public Result delete(@PathVariable("cid") Integer cid) {
        replyService.remove(new QueryWrapper<Reply>().eq("cid", cid));
        commentService.removeById(cid);
        return Result.succ("删除成功");
    }
}

package com.ssp.vue.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Equivalence;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Blog;
import com.ssp.vue.blog.entity.Comment;
import com.ssp.vue.blog.entity.Reply;
import com.ssp.vue.blog.service.CommentService;
import com.ssp.vue.blog.service.ReplyService;
import com.ssp.vue.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ReplyController {

    @Autowired
    ReplyService replyService;
    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;

    @PostMapping("/replies")
    public Result post(@RequestBody Reply reply) {
        reply.setCreateDate(LocalDateTime.now());
        reply.setNickname(userService.getById(1).getNickname());
        replyService.save(reply);
        Comment comment = commentService.getById(reply.getCid());
        comment.setStatue(true);
        commentService.saveOrUpdate(comment);
        return Result.succ("回复成功");
    }
}

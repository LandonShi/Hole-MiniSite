package com.ssp.vue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ssp.vue.blog.entity.Comment;
import com.ssp.vue.blog.mapper.CommentMapper;
import com.ssp.vue.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}

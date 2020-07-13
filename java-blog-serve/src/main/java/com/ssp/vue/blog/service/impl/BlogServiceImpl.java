package com.ssp.vue.blog.service.impl;

import com.ssp.vue.blog.entity.Blog;
import com.ssp.vue.blog.mapper.BlogMapper;
import com.ssp.vue.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Integer saveBlog(Blog blog) {

        return blogMapper.saveBlog(blog);
    }
}

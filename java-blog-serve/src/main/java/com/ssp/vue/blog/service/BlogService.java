package com.ssp.vue.blog.service;

import com.ssp.vue.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;


public interface BlogService extends IService<Blog> {

    Integer saveBlog(Blog blog);

}

package com.ssp.vue.blog.mapper;

import com.ssp.vue.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface BlogMapper extends BaseMapper<Blog> {
    Integer saveBlog(Blog blog);
}

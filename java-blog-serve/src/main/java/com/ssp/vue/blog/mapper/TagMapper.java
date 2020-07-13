package com.ssp.vue.blog.mapper;

import com.ssp.vue.blog.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {
    void saveTagAndBlog(@Param("blog_id") Integer bid, @Param("tag_id") Integer tid);

    List<Integer> findTagsByBlog(@Param("blog_id") Long bid);

    void removeTagByBlog(@Param("blog_id") Integer bid);

    List<Integer> listTagBlogs(@Param("tag_id") Integer tagID);
}

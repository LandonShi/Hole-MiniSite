package com.ssp.vue.blog.service;

import com.ssp.vue.blog.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ssp
 * @since 2020-06-12
 */
public interface TagService extends IService<Tag> {
    void saveTagAndBlog(Integer bid, Integer tid);

    List<Integer> findTagsByBlog(Long bid);

    void removeTagByBlog(Integer bid);

    List<Integer> listTagBlogs(Integer tagID);
}

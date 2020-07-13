package com.ssp.vue.blog.service.impl;

import com.ssp.vue.blog.entity.Tag;
import com.ssp.vue.blog.mapper.TagMapper;
import com.ssp.vue.blog.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ssp
 * @since 2020-06-12
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Autowired
    TagMapper tagMapper;

    @Override
    public void saveTagAndBlog(Integer bid, Integer tid) {
        tagMapper.saveTagAndBlog(bid, tid);
    }

    @Override
    public List<Integer> findTagsByBlog(Long bid) {
        return tagMapper.findTagsByBlog(bid);
    }

    @Override
    public void removeTagByBlog(Integer bid) {
        tagMapper.removeTagByBlog(bid);
    }

    @Override
    public List<Integer> listTagBlogs(Integer tagID) {
        return tagMapper.listTagBlogs(tagID);
    }


}

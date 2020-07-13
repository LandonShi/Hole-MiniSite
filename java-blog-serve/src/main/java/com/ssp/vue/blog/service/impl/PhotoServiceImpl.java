package com.ssp.vue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssp.vue.blog.entity.Photo;
import com.ssp.vue.blog.mapper.PhotoMapper;
import com.ssp.vue.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Autowired
    PhotoMapper photoMapper;

    @Override
    public Integer saveEntity(Photo photo) {
        return photoMapper.insertOne(photo);
    }

    @Override
    public void updateByAddress(Integer id, String address) {
        photoMapper.updateByAddress(id, address);
    }

    @Override
    public List<Photo> findAllByAtlas(Integer aid) {
        return photoMapper.findAllByAtlas(aid);
    }

    @Override
    public Integer getCountByAtlas(Integer aid) {
        return photoMapper.getCountByAtlas(aid);
    }
}

package com.ssp.vue.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssp.vue.blog.entity.Photo;

import java.util.List;

public interface PhotoService  extends IService<Photo> {

    Integer saveEntity(Photo atlas);

    void updateByAddress(Integer id, String address);

    List<Photo> findAllByAtlas(Integer aid);

    Integer getCountByAtlas(Integer aid);
}

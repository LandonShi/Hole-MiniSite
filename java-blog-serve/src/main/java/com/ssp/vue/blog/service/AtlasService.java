package com.ssp.vue.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ssp.vue.blog.entity.Atlas;

public interface AtlasService extends IService<Atlas> {

    void removeAtlasAndPhotoById(Integer aid);

    Atlas getOneRec();
}

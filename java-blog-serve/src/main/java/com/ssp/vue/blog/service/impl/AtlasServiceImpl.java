package com.ssp.vue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssp.vue.blog.entity.Atlas;
import com.ssp.vue.blog.mapper.AtlasMapper;
import com.ssp.vue.blog.service.AtlasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtlasServiceImpl extends ServiceImpl<AtlasMapper, Atlas> implements AtlasService {

    @Autowired
    AtlasMapper atlasMapper;


    @Override
    public void removeAtlasAndPhotoById(Integer aid) {
        atlasMapper.removeAtlasAndPhotoById(aid);
    }

    @Override
    public Atlas getOneRec() {
        return atlasMapper.getOneRec();
    }
}

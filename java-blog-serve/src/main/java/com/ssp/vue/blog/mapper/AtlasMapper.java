package com.ssp.vue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssp.vue.blog.entity.Atlas;

import javax.websocket.server.PathParam;

public interface AtlasMapper extends BaseMapper<Atlas> {

    void removeAtlasAndPhotoById(@PathParam("id") Integer id);

    Atlas getOneRec();
}

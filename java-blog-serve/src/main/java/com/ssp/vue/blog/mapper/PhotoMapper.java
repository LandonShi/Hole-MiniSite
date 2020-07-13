package com.ssp.vue.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ssp.vue.blog.entity.Photo;
import org.apache.ibatis.annotations.Param;

import javax.websocket.server.PathParam;
import java.util.List;

public interface PhotoMapper extends BaseMapper<Photo> {


    Integer insertOne(Photo photo);

    void updateByAddress(@Param("id") Integer id, @Param("address") String address);

    List<Photo> findAllByAtlas(@Param("atlas_id") Integer atlas_id);

    Integer getCountByAtlas(@PathParam("atlas_id") Integer atlas_id);

    Photo getOne();
}

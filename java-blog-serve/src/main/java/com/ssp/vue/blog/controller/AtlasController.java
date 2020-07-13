package com.ssp.vue.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Atlas;

import com.ssp.vue.blog.entity.Photo;
import com.ssp.vue.blog.service.AtlasService;

import com.ssp.vue.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;


@RestController
public class AtlasController {

    @Autowired
    AtlasService atlasService;
    @Autowired
    PhotoService photoService;

    @PostMapping("/atlas")
    public Object addAtlas(@RequestBody Atlas atlas) {
        atlas.setStatue(false);
        atlas.setCreateDate(LocalDateTime.now());
        atlasService.save(atlas);
        return Result.succ("新增成功");
    }

    @GetMapping("/atlas")
    public Object listAndPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page<Atlas> page = new Page<>(currentPage, 10);
        IPage<Atlas> pageData = atlasService.page(page, new QueryWrapper<Atlas>().orderByDesc("id"));
        return Result.succ(pageData);
    }

    @GetMapping("/atlas/{aid}")
    public Object getAtlasOne(@PathVariable("aid") String aid) {
        Integer id = Integer.valueOf(aid);
        Atlas atlas = atlasService.getById(id);
        atlas.setPhotos(photoService.list(new QueryWrapper<Photo>().eq("atlas_id", aid)));
        return Result.succ(atlas);
    }

    @PutMapping("/atlas")
    public Object updateAtlas(@RequestBody Atlas atlas) {
        atlasService.updateById(atlas);
        return Result.succ("更新成功");
    }

    @DeleteMapping("/atlas")
    public Object deleteAtlas(@RequestParam("aid") Integer aid, HttpServletRequest request) {
        //批量删除图片文件
        File imageFolder = new File(request.getServletContext().getRealPath("img/photo") + aid);
        removeFileByOne(imageFolder);
        //删除图集记录同时删除图片记录
        atlasService.removeAtlasAndPhotoById(aid);
        return Result.succ("删除成功");
    }
    private void removeFileByOne(File imageFolder) {
        if(imageFolder.exists()) {
            if(imageFolder.isFile()) {
                imageFolder.delete();
            } else {
                File[] listFiles = imageFolder.listFiles();
                assert listFiles != null;
                if(listFiles.length > 0) {
                    for (File file: listFiles) {
                        removeFileByOne(file);
                    }
                } else {
                    imageFolder.delete();
                }
            }
        } else {
            System.out.println("路径不存在");
        }
    }

    @DeleteMapping("/atlases")
    public Object deleteSelBatchAtlas(@RequestParam("arr") String arr, HttpServletRequest request) {
        String[] aars = arr.split(",");
        for (String aar : aars) {
            //批量删除图片文件
            Integer atlas_id = Integer.valueOf(aar);
            File imageFolder = new File(request.getServletContext().getRealPath("img/photo") + atlas_id);
            removeFileByOne(imageFolder);
            //删除图集记录同时删除图片记录
            atlasService.removeAtlasAndPhotoById(atlas_id);
        }
        return Result.succ("操作成功");
    }
}

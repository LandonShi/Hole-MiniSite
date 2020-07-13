package com.ssp.vue.blog.controller;

import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Atlas;
import com.ssp.vue.blog.entity.Photo;
import com.ssp.vue.blog.service.AtlasService;
import com.ssp.vue.blog.service.PhotoService;
import com.ssp.vue.blog.common.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class PhotoController {

    @Autowired
    PhotoService photoService;
    @Autowired
    AtlasService atlasService;

    @PostMapping("/photo")
    public Object addPhoto(@RequestParam("name") String name,
                           @RequestParam("description") String description,
                           @RequestParam("aid") Integer aid,
                           MultipartFile image, HttpServletRequest request) throws IOException {
        Photo photo = new Photo();
        Atlas atlas = atlasService.getById(aid);
        photo.setAtlas(atlas);
        photo.setName(name);
        photo.setDescription(description);
        photo.setCreateDate(LocalDateTime.now());
        photo.setAddress(null);
        //插入值返回主键id
        photoService.saveEntity(photo);
        //更新图集状态，代表已经有文件存储进去了
        if(!atlas.isStatue())
            atlas.setStatue(true);
        atlasService.saveOrUpdate(atlas);
        //更新地址
        String address = saveOrUpdateImageFile(photo.getId(), image, request,aid);
        photoService.updateByAddress(photo.getId(), address);
        return Result.succ("添加成功");
    }
    private String saveOrUpdateImageFile(Integer id, MultipartFile image, HttpServletRequest request,
                                         Integer aid) throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/photo" + aid));
        File file = new File(imageFolder,id + ".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        //做格式转换
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
        //保存完图片之后，需要将地址返回
        return file.toString();
    }

    @GetMapping("/photo")
    public Object listPhotos(@RequestParam("aid") Integer aid) {
        List<Photo> photo = photoService.findAllByAtlas(aid);
        return Result.succ(photo);
    }

    @DeleteMapping("/photo")
    public Object deletePhoto(@RequestParam("pid") Integer pid, @RequestParam("aid") Integer aid,  HttpServletRequest request) {
        photoService.removeById(pid);
        deletePhoto(request, aid, pid);
        return Result.succ("删除成功");
    }
    private void deletePhoto(HttpServletRequest request, Integer aid ,Integer pid) {
        File imageFolder = new File(request.getServletContext().getRealPath("img/photo" + aid));
        File file = new File(imageFolder,pid + ".jpg");
        file.delete();
    }

}

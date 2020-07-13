package com.ssp.vue.blog.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.common.util.ImageUtil;
import com.ssp.vue.blog.common.util.JwtUtil;
import com.ssp.vue.blog.common.util.RedisUtil;
import com.ssp.vue.blog.entity.User;
import com.ssp.vue.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    RedisUtil redisUtil;

    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("/user")
    public Object user() {
        User user = userService.getById(1);
        return Result.succ(user);
    }

    @PostMapping("/user")
    public Object changeAvatar(MultipartFile image, HttpServletRequest request) throws IOException {
        User user = userService.getById(1);
        System.out.println(user.toString());
        String address = saveOrUpdateImageFile(user.getId(), image, request);
        user.setAvatar(address);
        userService.updateById(user);
        return Result.succ("更新成功");
    }
    private String saveOrUpdateImageFile(Integer id, MultipartFile image, HttpServletRequest request) throws IOException {
        File imageFolder = new File(request.getServletContext().getRealPath("img/avatar"));
        File file = new File(imageFolder,id + ".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        //做格式转换
        BufferedImage img = ImageUtil.change2jpg(file);
        assert img != null;
        ImageIO.write(img, "jpg", file);
        //保存完图片之后，需要将地址返回
        return file.toString();
    }

    @PutMapping("/user/nickname")
    public Object changeNickname(@RequestParam("nickname") String nickname, HttpServletRequest request) {
        User u = getStringFromStream(request);
        assert u != null;
        //查询出数据库中用户的基本信息,可能存在redis数据未及时刷新
        User user = userService.getById(u.getId());
        user.setNickname(nickname);
        userService.updateById(user);
        return Result.succ("更新成功");
    }

    @PutMapping("/user/description")
    public Object changeDescription(@RequestParam("description") String description, HttpServletRequest request) {
        User u = getStringFromStream(request);
        assert u != null;
        //查询出数据库中用户的基本信息
        User user = userService.getById(u.getId());
        user.setDescription(description);
        userService.updateById(user);
        return Result.succ("更新成功");
    }

    /*
    获取 Http中的requestload中 headers值,并将其转换成对象返回
     */
    private User getStringFromStream(HttpServletRequest req) {
        ServletInputStream is;
        try {
            is = req.getInputStream();
            int nRead = 1;
            int nTotalRead = 0;
            byte[] bytes = new byte[10240];
            while (nRead > 0) {
                nRead = is.read(bytes, nTotalRead, bytes.length - nTotalRead);
                if (nRead > 0)
                    nTotalRead = nTotalRead + nRead;
            }
            String str = new String(bytes, 0, nTotalRead, StandardCharsets.UTF_8);
            JSONObject jsonObject = (JSONObject) JSONObject.parseObject(str).get("headers");
            //获取键
            String jwt = (String) jsonObject.get("Authorization");
            //从redis 获取到的是 json对象
            return JSONObject.toJavaObject((JSON) redisUtil.get(jwt), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}

package com.ssp.vue.blog.controller;

import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.User;
import com.ssp.vue.blog.entity.Webcom;
import com.ssp.vue.blog.entity.Webrep;
import com.ssp.vue.blog.service.UserService;
import com.ssp.vue.blog.service.WebcomService;
import com.ssp.vue.blog.service.WebrepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


@RestController
public class WebrepController {

    @Autowired
    WebrepService webrepService;
    @Autowired
    UserService userService;
    @Autowired
    WebcomService webcomService;

    @PostMapping("/webrepes")
    public Result add(@RequestBody Webrep webrep) {
        webrep.setCreateDate(LocalDateTime.now());
        webrep.setNickname(userService.getById(1).getNickname());
        webrepService.save(webrep);
        Webcom webcom = webcomService.getById(webrep.getComID());
        if(!webcom.isStatue())
            webcom.setStatue(true);
        webcomService.saveOrUpdate(webcom);
        return Result.succ("操作成功");
    }
}

package com.ssp.vue.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Log;
import com.ssp.vue.blog.service.LogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/log")
    public Object getLogsAndPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page<Log> page = new Page<>(currentPage, 10);
        IPage<Log> pageData = logService.page(page, new QueryWrapper<Log>().orderByDesc("id"));
        return Result.succ(pageData);
    }
}

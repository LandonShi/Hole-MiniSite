package com.ssp.vue.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Link;
import com.ssp.vue.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class LinkController {

    @Autowired
    LinkService linkService;

    @PostMapping("/links")
    public Result add(@RequestBody Link link) {
        link.setCreateDate(LocalDateTime.now());
        link.setStatue(true);
        System.out.println(link.toString());
        linkService.save(link);
        return Result.succ("操作成功");
    }

    @GetMapping("/links")
    public Result listAndPage(@RequestParam(defaultValue = "1") Long currentPage) {
        Page<Link> page = new Page<>(currentPage, 10);
        IPage<Link> pageData = linkService.page(page, new QueryWrapper<Link>().orderByDesc("id"));
        return Result.succ(pageData);
    }

    @GetMapping("/links/{lid}")
    public Result findOne(@PathVariable("lid") Integer lid) {
        Link link = linkService.getById(lid);
        return Result.succ(link);
    }

    @PutMapping("/links")
    public Result putLink(@RequestBody Link link) {
        linkService.saveOrUpdate(link);
        return Result.succ("操作成功");
    }

    @PutMapping("/links/{lid}")
    public Result putStatueLink(@PathVariable("lid") Integer lid) {
        Link link = linkService.getById(lid);
        link.setStatue(!link.isStatue());
        linkService.saveOrUpdate(link);
        return Result.succ("设置成功");
    }

    @DeleteMapping("/links/{lid}")
    public Result deleteLink(@PathVariable("lid") Integer lid) {
        linkService.removeById(lid);
        return Result.succ("操作成功");
    }

    @DeleteMapping("/links_set")
    public Object deleteSelBatchBlog(@RequestParam("arr") String arr) {
        String[] aars = arr.split(",");
        for (String aar : aars) {
            Integer link_id = Integer.valueOf(aar);
            linkService.removeById(link_id);
        }
        return Result.succ("操作成功");
    }
}

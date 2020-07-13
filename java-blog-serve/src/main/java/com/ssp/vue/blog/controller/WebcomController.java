package com.ssp.vue.blog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Webcom;
import com.ssp.vue.blog.entity.Webrep;
import com.ssp.vue.blog.service.WebcomService;
import com.ssp.vue.blog.service.WebrepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class WebcomController {

    @Autowired
    WebcomService webcomService;
    @Autowired
    WebrepService webrepService;

    @PostMapping("/webcomes")
    public Result add(@RequestBody Webcom webcom) {
        webcom.setCreateDate(LocalDateTime.now());
        webcom.setStatue(false);
        webcomService.save(webcom);
        return Result.succ("留言已收到，站长正快马加鞭赶过来！");
    }

    @GetMapping("/webcomes")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page<Webcom> page = new Page<>(currentPage, 10);
        IPage<Webcom> pageData = webcomService.page(page, new QueryWrapper<Webcom>().orderByDesc("id"));
        return Result.succ(pageData);
    }

    @GetMapping("/webcomes/statue")
    public Result getStatue(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam("statue") Integer statue) {
        boolean flag = true;
        if(statue == 0)
            flag = false;
        List<Webcom> webcoms = webcomService.list(new QueryWrapper<Webcom>().eq("statue", flag));
        for(Webcom webcom : webcoms) {
            List<Webrep> webreps = webrepService.list(new QueryWrapper<Webrep>().eq("com_id", webcom.getId()));
            webcom.setWebreps(webreps);
        }
        IPage<Webcom> page = new Page<>(currentPage, 10);
        page.setRecords(webcoms);
        //总记录数
        page.setTotal(webcoms.size());
        //当前分页位置
        page.setCurrent(currentPage);
        return Result.succ(page);
    }

    @GetMapping("/webcomes/info/{comid}")
    public Result getOne(@PathVariable("comid") Integer comid) {
        Webcom webcom = webcomService.getById(comid);
        List<Webrep> webreps = webrepService.list(new QueryWrapper<Webrep>().eq("com_id", comid));
        webcom.setWebreps(webreps);
        return Result.succ(webcom);
    }

    @DeleteMapping("/webcomes/{comID}")
    public Result delete(@PathVariable("comID") Integer comID) {
        webrepService.remove(new QueryWrapper<Webrep>().eq("com_id", comID));
        webcomService.removeById(comID);
        return Result.succ("删除成功");
    }


}

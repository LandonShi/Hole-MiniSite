package com.ssp.vue.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ssp.vue.blog.common.result.Result;
import com.ssp.vue.blog.entity.Blog;
import com.ssp.vue.blog.entity.Type;
import com.ssp.vue.blog.service.BlogService;
import com.ssp.vue.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
public class TypeController {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;

    @GetMapping("/types")
    public Object listAndPage(@RequestParam(defaultValue = "1") Integer currentPage) {
        List<Type> typeList = typeService.list(new QueryWrapper<Type>().orderByDesc("id"));
        for(Type type: typeList)
            type.setBlogList(blogService.list(new QueryWrapper<Blog>().eq("tid", type.getId())));
        IPage<Type> page = new Page<>(currentPage, 10);
        page.setRecords(typeList);
        //总记录数
        page.setTotal(typeList.size());
        //当前分页位置
        page.setCurrent(1);
        return Result.succ(page);
    }

    @GetMapping("/types/{tid}")
    public Object getTypeOne(@PathVariable("tid") Integer tid) {
        Type type = typeService.getById(tid);
        return Result.succ(type);
    }

    @PostMapping("/types")
    public Object addType(@RequestBody Type type) {
        type.setCreateDate(LocalDateTime.now());
        typeService.save(type);
        return Result.succ("新增成功");
    }

    @PutMapping("/types")
    public Object updateType(@RequestBody Type type) {
        typeService.updateById(type);
        return Result.succ("更新成功");
    }

    @DeleteMapping("/types")
    public Object deleteAtlas(@RequestParam("tid") Integer tid) {
        typeService.removeById(tid);
        return Result.succ("删除成功");
    }

    @DeleteMapping("/typeset")
    public Object deleteSelBatchAtlas(@RequestParam("arr") String arr) {
        String[] aars = arr.split(",");
        for (String aar : aars) {
            Integer type_id = Integer.valueOf(aar);
            typeService.removeById(type_id);
        }
        return Result.succ("操作成功");
    }
}

package com.ssp.vue.blog.common.vo;

import com.ssp.vue.blog.entity.*;
import lombok.Data;

import java.util.List;

@Data
public class WebSiteVO {

    private List<Webcom> webcomes;

    private List<Comment> comments;

    private List<Atlas> atlases;

    private List<Blog> blogs;

    private List<Tag> tags;

    private List<Type> types;

    private List<Link> links;

    private Integer views;
}

package com.ssp.vue.blog.common.vo;

import com.ssp.vue.blog.entity.Comment;
import com.ssp.vue.blog.entity.Webcom;
import lombok.Data;

import java.util.List;

@Data
public class PersonValueVO {

    private List<Webcom> webcomes;
    private List<Comment> comments;

}

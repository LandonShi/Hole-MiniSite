package com.ssp.vue.blog.common.vo;

import com.ssp.vue.blog.entity.Link;
import lombok.Data;

import java.util.List;

@Data
public class CountVO {

    private Integer blogCount;

    private Integer tagCount;

    private Integer typeCount;

    private List<Link> links;

}

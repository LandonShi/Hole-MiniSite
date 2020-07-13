package com.ssp.vue.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "description")
    private String description;

    @TableField(value = "content")
    private String content;

    @TableField(value = "statue")
    private Integer statue;

    @TableField(value = "recommend")
    private Boolean recommend;

    @TableField(value = "views")
    private Integer views;

    @TableField(value = "createDate")
    private LocalDateTime createDate;

    @TableField(value = "updateDate")
    private LocalDateTime updateDate;

    @TableField(value = "tid")
    private Integer tid;

    @TableField(value = "uid")
    private Integer uid;

    @TableField(exist = false)
    private Type type;  //数据库中不存在字段

    @TableField(exist = false)
    private User user;  //数据库中不存在字段

    @TableField(exist = false)
    private List<Integer> tags;  //数据库中不存在字段

    @TableField(exist = false)
    private List<Comment> comments;

}

package com.ssp.vue.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "comment")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "content")
    private String content;

    @TableField(value = "createDate")
    private LocalDateTime createDate;

    @TableField(value = "email")
    private String email;

    @TableField(value = "statue")
    private boolean statue;

    @TableField(value = "bid")
    private Integer bid;

    @TableField(exist = false)
    private Blog blog;

    @TableField(exist = false)
    private List<Reply> replies;
}

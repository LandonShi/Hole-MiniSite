package com.ssp.vue.blog.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssp.vue.blog.entity.Reply;
import com.ssp.vue.blog.mapper.ReplyMapper;
import com.ssp.vue.blog.service.ReplyService;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

}

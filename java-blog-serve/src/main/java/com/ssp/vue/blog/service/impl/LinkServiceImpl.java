package com.ssp.vue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssp.vue.blog.entity.Link;
import com.ssp.vue.blog.mapper.LinkMapper;
import com.ssp.vue.blog.service.LinkService;
import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

}

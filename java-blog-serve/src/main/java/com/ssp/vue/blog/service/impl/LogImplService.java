package com.ssp.vue.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ssp.vue.blog.entity.Log;
import com.ssp.vue.blog.mapper.LogMapper;
import com.ssp.vue.blog.service.LogService;
import org.springframework.stereotype.Service;


@Service
public class LogImplService extends ServiceImpl<LogMapper, Log> implements LogService {


}

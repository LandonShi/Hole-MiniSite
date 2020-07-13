package com.ssp.vue.blog.service.impl;

import com.ssp.vue.blog.entity.User;
import com.ssp.vue.blog.mapper.UserMapper;
import com.ssp.vue.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ssp
 * @since 2020-06-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

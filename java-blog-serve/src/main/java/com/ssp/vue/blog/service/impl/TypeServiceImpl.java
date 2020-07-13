package com.ssp.vue.blog.service.impl;

import com.ssp.vue.blog.entity.Type;
import com.ssp.vue.blog.mapper.TypeMapper;
import com.ssp.vue.blog.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {

}

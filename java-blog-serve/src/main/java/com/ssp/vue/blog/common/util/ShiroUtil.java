package com.ssp.vue.blog.common.util;


import com.ssp.vue.blog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static AccountProfile getProfile() {

        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

}

package com.z.admin.security;

import com.z.admin.entity.po.system.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author zhy
 * @description
 * @date 2022/12/21
 */
public class UserDetail extends User {
    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象。（这里我就不写get/set方法了）
     */
    private SystemUser systemUser;

    public UserDetail(SystemUser systemUser, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，以初始化用户名、密码、权限
        super(systemUser.getUsername(), systemUser.getPassword(), authorities);
        this.systemUser = systemUser;
    }
}
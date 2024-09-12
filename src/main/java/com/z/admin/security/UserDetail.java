package com.z.admin.security;

import com.z.admin.entity.po.SystemUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author zhy
 * @description 登录用户封装
 * @date 2022/12/21
 */
@Getter
public class UserDetail extends User {
    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象。
     */
    private final SystemUser systemUser;

    public UserDetail(SystemUser systemUser, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，以初始化用户名、密码、权限
        super(systemUser.getUsername(), systemUser.getPassword(), authorities);
        this.systemUser = systemUser;
    }
}
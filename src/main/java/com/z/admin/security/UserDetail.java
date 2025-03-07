package com.z.admin.security;

import com.z.admin.entity.dto.UserLoginDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author zhy
 * @description 登录用户封装
 */
@Getter
public class UserDetail extends User {

    /**
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象
     */
    private final UserLoginDto userLoginDto;

    public UserDetail(UserLoginDto userLoginDto, Collection<? extends GrantedAuthority> authorities) {
        super(userLoginDto.getUsername(), "", authorities);
        this.userLoginDto = userLoginDto;
    }

}
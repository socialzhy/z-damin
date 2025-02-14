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
     * 我们自己的用户实体对象，要调取用户信息时直接获取这个实体对象。
     */
    private final UserLoginDto userLoginDto;

//    public UserDetail(SystemUser systemUser, List<Long> roleList, List<Long> permissionList, Collection<? extends GrantedAuthority> authorities) {
//        // 必须调用父类的构造方法，以初始化用户名、密码、权限
//        super(systemUser.getUsername(), systemUser.getPassword(), authorities);
//        this.userLoginDto = UserLoginDto.of(systemUser,roleList,permissionList);
//    }

    public UserDetail(UserLoginDto userLoginDto, Collection<? extends GrantedAuthority> authorities) {
        // 必须调用父类的构造方法，以初始化用户名、密码、权限
//        super(userLoginDto.getUsername(), userLoginDto.getPassword(),true,true,true,true, authorities);
        super(userLoginDto.getUsername(), userLoginDto.getPassword(), true, true, true, true, authorities);
        this.userLoginDto = userLoginDto;
    }

}
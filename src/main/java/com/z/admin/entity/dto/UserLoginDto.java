package com.z.admin.entity.dto;

import com.z.admin.entity.po.SystemUser;
import com.z.admin.util.BeanUtils;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhy
 * @description 用户登录信息
 */
@Data
public class UserLoginDto {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 角色集合
     */
    private List<Long> roleList = new ArrayList<>();

    /**
     * 权限集合, 用户指定权限
     */
    private List<Long> permissionList = new ArrayList<>();

    /**
     * 登录token
     */
    private String token;

    public static UserLoginDto of(SystemUser systemUser, List<Long> roleList, List<Long> permissionList, String token) {
        UserLoginDto dto = BeanUtils.copyProperties(systemUser, UserLoginDto.class);
        dto.setRoleList(roleList);
        dto.setPermissionList(permissionList);
        dto.setToken(token);
        return dto;
    }
}

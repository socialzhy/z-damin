package com.z.admin.entity.dto;

import com.z.admin.entity.po.SystemUser;
import com.z.admin.entity.vo.system.UserInfoVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author zhy
 * @description 用户登录信息
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginDto extends UserInfoVo {

    private String password;

    private String token;

    public static UserLoginDto of(SystemUser systemUser, List<Long> roleList, List<Long> permissionList,String token){
        UserLoginDto dto = systemUser.toVO(UserLoginDto.class);
        dto.setRoleList(roleList);
        dto.setPermissionList(permissionList);
        dto.setPassword(systemUser.getPassword());
        dto.setToken(token);
        return dto;
    }
}

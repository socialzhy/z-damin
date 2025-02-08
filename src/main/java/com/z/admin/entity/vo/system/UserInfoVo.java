package com.z.admin.entity.vo.system;

import com.z.admin.entity.vo.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhy
 * @description 用户信息
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserInfoVo extends BaseVo {

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
     * 权限集合
     */
    private List<Long> permissionList = new ArrayList<>();
}

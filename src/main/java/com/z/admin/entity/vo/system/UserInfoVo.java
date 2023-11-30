package com.z.admin.entity.vo.system;

import com.z.admin.entity.vo.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhy
 * @description 用户信息
 * @date 2022/10/19
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

//    /**
//     * 角色集合
//     */
//    private List<String> roles = List.of("admin");

    /**
     * 角色集合
     */
    private List<String> roleList = new ArrayList<>();

    /**
     * 页面权限
     */
    private List<Integer> pagePermissionList = new ArrayList<>();

    /**
     * 操作权限
     */
    private List<Integer> operationPermissionList = new ArrayList<>();
}

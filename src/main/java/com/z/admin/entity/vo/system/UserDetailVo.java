package com.z.admin.entity.vo.system;

import com.z.admin.entity.vo.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhy
 * @description 用户详情
 * @date 2022/10/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserDetailVo extends BaseVo {

    /**
     * 手机号
     */

    private String mobile;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 角色集合
     */
    private List<String> roles = List.of("adminz");

    /**
     * 权限集合
     */
    private List<String> permissions = new ArrayList<>();

}

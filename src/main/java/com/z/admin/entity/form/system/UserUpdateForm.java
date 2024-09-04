package com.z.admin.entity.form.system;

import com.z.admin.entity.form.base.BaseForm;
import com.z.admin.entity.po.SystemUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;

/**
 * @author zhy
 * @description
 * @date 2022/10/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserUpdateForm extends BaseForm<SystemUser> {

    /**
     * 用户主键id
     */
    private Long id;

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
    @Email
    private String email;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 昵称
     */
    private String nickName;
}

package com.z.admin.entity.form.system;

import com.z.admin.entity.form.base.BaseForm;
import com.z.admin.entity.po.SystemUser;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

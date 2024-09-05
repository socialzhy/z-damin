package com.z.admin.entity.form.system;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zhy
 * @description 登录form
 * @date 2022/9/21
 */
@Data
public class UserLoginForm {

    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

}

package com.z.admin.entity.form.system;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author zhy
 * @description
 * @date 2022/9/21
 */
@Data
public class UserLoginForm {

    @NotBlank
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

//    @Range(min = 2, max = 5, message = "范围不对")
//    private Integer a = 0;
}

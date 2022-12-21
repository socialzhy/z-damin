package com.z.admin.entity.form.system;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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

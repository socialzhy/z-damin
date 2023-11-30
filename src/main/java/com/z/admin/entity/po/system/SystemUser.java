package com.z.admin.entity.po.system;

import com.z.admin.entity.po.base.CommonPo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @date 2022-10-17
 * @description 用户类实体
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemUser extends CommonPo {

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
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

}

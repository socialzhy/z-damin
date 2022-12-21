package com.z.admin.entity.vo.system;

import com.z.admin.entity.vo.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @description
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
     * 密码
     */
    private String pwd;

    /**
     * 昵称
     */
    private String nickName;

}

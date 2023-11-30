package com.z.admin.entity.vo.system;

import com.z.admin.entity.po.system.SystemUser;
import com.z.admin.util.BeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @description 用户列表vo
 * @date 2022/10/19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo {

    /**
     * 用户id
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
    private String email;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 昵称
     */
    private String nickName;

    public UserVo(SystemUser user) {
        BeanUtils.copyPropertiesIgnoreNull(user, this);
    }
}

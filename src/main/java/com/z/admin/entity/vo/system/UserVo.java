package com.z.admin.entity.vo.system;

import com.z.admin.entity.po.SystemUser;
import com.z.admin.entity.vo.base.BaseVo;
import com.z.admin.util.BeanUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @description 用户列表vo
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserVo extends BaseVo {

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

    public UserVo(SystemUser user) {
        BeanUtils.copyPropertiesIgnoreNull(user, this);
    }
}

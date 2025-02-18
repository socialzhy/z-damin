package com.z.admin.entity.param.system;

import com.z.admin.entity.param.base.BasePageParam;
import com.z.admin.entity.po.SystemUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户列表查询参数
 *
 * @author zhy
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserQueryParam extends BasePageParam<SystemUser> {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 昵称
     */
    private String nickName;


}

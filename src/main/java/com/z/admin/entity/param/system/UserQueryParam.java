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

    private Long id;

    private String mobile;

    private String nickName;


}

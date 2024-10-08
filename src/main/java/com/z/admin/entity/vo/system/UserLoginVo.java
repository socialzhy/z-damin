package com.z.admin.entity.vo.system;

import com.z.admin.entity.vo.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @description
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginVo extends BaseVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * token
     */
    private String token;
}

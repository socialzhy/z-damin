package com.z.admin.entity.vo.system;

import com.z.admin.entity.vo.base.BaseVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhy
 * @description
 * @date 2022/9/21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserLoginVo extends BaseVo {

    private String username;

    private String token;
}

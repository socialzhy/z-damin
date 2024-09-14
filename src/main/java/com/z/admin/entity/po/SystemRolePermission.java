package com.z.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.z.admin.entity.po.base.BasePo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色权限关联
 * </p>
 *
 * @author system
 * @since 2024-09-14
 */
@Getter
@Setter
@TableName("system_role_permission")
public class SystemRolePermission extends BasePo {

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;
}

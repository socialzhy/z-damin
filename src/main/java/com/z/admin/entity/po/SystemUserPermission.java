package com.z.admin.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.z.admin.entity.po.base.CommonPo;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户权限关联
 * </p>
 *
 * @author system
 * @since 2024-09-12
 */
@Getter
@Setter
@TableName("system_user_permission")
public class SystemUserPermission extends CommonPo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 权限id
     */
    private Long permissionId;
}

package com.z.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z.admin.entity.po.SystemUserPermission;

import java.util.List;

/**
 * <p>
 * 用户权限关联 服务类
 * </p>
 *
 * @author system
 * @since 2024-09-04
 */
public interface ISystemUserPermissionService extends IService<SystemUserPermission> {

    /**
     * 根据userId查询权限id集合
     */
    List<Long> queryPermissionByUserId(Long userId);
}

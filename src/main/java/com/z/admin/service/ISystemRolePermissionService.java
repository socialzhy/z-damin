package com.z.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z.admin.entity.po.SystemRolePermission;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author system
 * @since 2024-09-09
 */
public interface ISystemRolePermissionService extends IService<SystemRolePermission> {

    /**
     * 根据角色id集合查询权限集合
     */
    List<Long> queryPermissionByRoleId(List<Long> roleIdList);
}

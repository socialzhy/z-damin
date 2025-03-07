package com.z.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z.admin.entity.po.SystemPermission;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author system
 * @since 2024-09-04
 */
public interface ISystemPermissionService extends IService<SystemPermission> {

    /**
     * 通过数据库查询权限
     */
    List<SystemPermission> queryOperationalPermission();

    /**
     * 查询匿名访问权限
     */
    List<String> queryAnonymousPermission();

}

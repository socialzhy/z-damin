package com.z.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.z.admin.entity.po.SystemUserRole;

import java.util.List;

/**
 * <p>
 * 用户角色关联 服务类
 * </p>
 *
 * @author system
 * @since 2024-09-04
 */
public interface ISystemUserRoleService extends IService<SystemUserRole> {

    /**
     * 根据userId查询角色id集合
     */
    List<Long> queryRoleByUserId(Long userId);
}

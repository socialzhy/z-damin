package com.z.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemUserPermissionMapper;
import com.z.admin.entity.po.SystemUserPermission;
import com.z.admin.service.ISystemUserPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户权限关联 服务实现类
 * </p>
 *
 * @author system
 * @since 2024-09-04
 */
@Service
public class SystemUserPermissionService extends ServiceImpl<SystemUserPermissionMapper, SystemUserPermission> implements ISystemUserPermissionService {

    @Override
    public List<Long> queryPermissionByUserId(Long userId) {
        LambdaQueryWrapper<SystemUserPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SystemUserPermission::getPermissionId)
                .eq(SystemUserPermission::getUserId, userId);
        return this.listObjs(wrapper, e -> (Long) e);
    }
}

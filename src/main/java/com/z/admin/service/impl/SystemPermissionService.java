package com.z.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemPermissionMapper;
import com.z.admin.entity.enums.SystemPermissionLevel;
import com.z.admin.entity.enums.SystemPermissionType;
import com.z.admin.entity.po.SystemPermission;
import com.z.admin.service.ISystemPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author system
 * @since 2024-09-04
 */
@Service
public class SystemPermissionService extends ServiceImpl<SystemPermissionMapper, SystemPermission> implements ISystemPermissionService {

    @Override
    public List<SystemPermission> queryOperationalPermission() {
        LambdaQueryWrapper<SystemPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemPermission::getType, SystemPermissionType.OPERATE.getId());
        return this.list(wrapper);
    }

    @Override
    public List<String> queryAnonymousPermission() {
        LambdaQueryWrapper<SystemPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SystemPermission::getId)
                .eq(SystemPermission::getAccessLevel, SystemPermissionLevel.ANONYMOUS.getId());
        return this.listObjs(wrapper, Object::toString);
    }
}

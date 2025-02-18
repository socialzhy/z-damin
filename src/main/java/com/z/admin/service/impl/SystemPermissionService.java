package com.z.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemPermissionMapper;
import com.z.admin.entity.enums.RedisKeyEnum;
import com.z.admin.entity.enums.SystemPermissionLevel;
import com.z.admin.entity.enums.SystemPermissionType;
import com.z.admin.entity.po.SystemPermission;
import com.z.admin.service.ISystemPermissionService;
import com.z.admin.util.DataUtils;
import com.z.admin.util.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisUtil redisUtil;

    @Override
    public List<SystemPermission> queryOperationalPermission() {
        List<SystemPermission> systemPermissionList = redisUtil.lGetAll(RedisKeyEnum.PERMISSION, SystemPermission.class);
        if (DataUtils.isEmpty(systemPermissionList)){
            LambdaQueryWrapper<SystemPermission> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SystemPermission::getType, SystemPermissionType.OPERATE.getId());
            systemPermissionList = this.list(wrapper);
            redisUtil.lSetAll(RedisKeyEnum.PERMISSION,systemPermissionList);
        }

        return systemPermissionList;
    }

    @Override
    public List<String> queryAnonymousPermission() {
        LambdaQueryWrapper<SystemPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(SystemPermission::getId)
                .eq(SystemPermission::getAccessLevel, SystemPermissionLevel.ANONYMOUS.getId());
        return this.listObjs(wrapper, Object::toString);
    }
}

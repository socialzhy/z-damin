package com.z.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemRolePermissionMapper;
import com.z.admin.entity.enums.RedisKeyEnum;
import com.z.admin.entity.po.SystemRolePermission;
import com.z.admin.service.ISystemRolePermissionService;
import com.z.admin.util.DataUtils;
import com.z.admin.util.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author system
 * @since 2024-09-09
 */
@Service
public class SystemRolePermissionService extends ServiceImpl<SystemRolePermissionMapper, SystemRolePermission> implements ISystemRolePermissionService {

    @Resource
    RedisUtil redisUtil;

    @Override
    public List<Long> queryPermissionByRoleId(List<Long> roleIdList) {
        if (DataUtils.isEmpty(roleIdList)) {
            return new ArrayList<>();
        }

        return this.redisUtil.lGetAll(RedisKeyEnum.ROLE_PERMISSION, SystemRolePermission.class)
                .stream()
                .map(SystemRolePermission::getPermissionId)
                .filter(roleIdList::contains)
                .toList();
    }
}

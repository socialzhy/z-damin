package com.z.admin.scheduled;

import com.z.admin.entity.enums.RedisKeyEnum;
import com.z.admin.entity.po.SystemPermission;
import com.z.admin.entity.po.SystemRolePermission;
import com.z.admin.service.ISystemPermissionService;
import com.z.admin.service.ISystemRolePermissionService;
import com.z.admin.util.DataUtils;
import com.z.admin.util.RedisUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhy
 * @description 服务启动依赖注入完成后执行逻辑
 */
@Component
@Slf4j
public class StartupRunner {

    @Resource
    ISystemPermissionService permissionService;
    @Resource
    ISystemRolePermissionService rolePermissionService;
    @Resource
    private RedisUtil redisUtil;

    /**
     * 加载权限路由
     */
    @PostConstruct
    public void loadPermission() {
        redisUtil.del(RedisKeyEnum.PERMISSION);
        List<SystemPermission> systemPermissionList = permissionService.queryOperationalPermission();
        if (DataUtils.isEmpty(systemPermissionList)) {
            throw new NullPointerException("systemPermission is must be not null!");
        }

        redisUtil.lSetAll(RedisKeyEnum.PERMISSION, systemPermissionList);
    }

    /**
     * 加载角色权限路由
     */
    @PostConstruct
    public void loadRolePermission() {
        redisUtil.del(RedisKeyEnum.ROLE_PERMISSION.getKey());

        List<SystemRolePermission> list = rolePermissionService.list();
        if (DataUtils.isEmpty(list)) {
            return;
        }

        redisUtil.lSetAll(RedisKeyEnum.ROLE_PERMISSION, list);
    }
}

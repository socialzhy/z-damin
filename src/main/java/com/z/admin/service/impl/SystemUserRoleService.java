package com.z.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemUserRoleMapper;
import com.z.admin.entity.po.SystemUserRole;
import com.z.admin.service.ISystemUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author system
 * @since 2024-09-04
 */
@Service
public class SystemUserRoleService extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements ISystemUserRoleService {

}

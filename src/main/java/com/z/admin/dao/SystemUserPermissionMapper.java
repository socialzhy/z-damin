package com.z.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z.admin.entity.po.SystemUserPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户权限关联 Mapper 接口
 * </p>
 *
 * @author system
 * @since 2024-09-06
 */
@Mapper
public interface SystemUserPermissionMapper extends BaseMapper<SystemUserPermission> {

}

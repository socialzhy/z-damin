package com.z.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z.admin.entity.po.SystemPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author system
 * @since 2024-09-06
 */
@Mapper
public interface SystemPermissionMapper extends BaseMapper<SystemPermission> {

}

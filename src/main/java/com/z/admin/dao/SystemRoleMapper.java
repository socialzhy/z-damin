package com.z.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z.admin.entity.po.system.SystemRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhy
 * @description 系统角色mapper
 * @date 2023/11/30
 */
@Mapper
public interface SystemRoleMapper extends BaseMapper<SystemRole> {
}

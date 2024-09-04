package com.z.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.z.admin.entity.po.SystemUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhy
 * @description
 * @date 2022/10/17
 */
@Mapper
public interface SystemUserMapper extends BaseMapper<SystemUser> {
}

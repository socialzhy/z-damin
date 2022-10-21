package com.z.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.z.admin.entity.param.system.UserQueryParam;
import com.z.admin.entity.po.system.SystemUser;
import com.z.admin.entity.vo.system.UserVo;

/**
 * @author zhy
 * @description
 * @date 2022/10/17
 */
public interface ISystemUserService extends IService<SystemUser> {

    IPage<UserVo> query(UserQueryParam param);
}

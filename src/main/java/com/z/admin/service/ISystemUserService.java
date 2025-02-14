package com.z.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.param.system.UserQueryParam;
import com.z.admin.entity.po.SystemUser;
import com.z.admin.entity.vo.system.UserInfoVo;
import com.z.admin.entity.vo.system.UserLoginVo;
import com.z.admin.entity.vo.system.UserVo;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author zhy
 * @description
 */
public interface ISystemUserService extends IService<SystemUser> {

    IPage<UserVo> query(UserQueryParam param);

    UserLoginVo login(UserLoginForm form);

    UserDetails loadUserByCache(String username);

    UserInfoVo info();
}

package com.z.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemUserMapper;
import com.z.admin.entity.param.system.UserQueryParam;
import com.z.admin.entity.po.system.SystemUser;
import com.z.admin.entity.vo.system.UserVo;
import com.z.admin.service.ISystemUserService;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 * @description 系统用户
 * @date 2022/10/17
 */
@Service
public class SystemUserService extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

    @Override
    public IPage<UserVo> query(UserQueryParam param) {
        IPage<SystemUser> iPageUser = this.page(param.getPage(), new QueryWrapper<>());
        return iPageUser.convert(UserVo::new);
    }
}

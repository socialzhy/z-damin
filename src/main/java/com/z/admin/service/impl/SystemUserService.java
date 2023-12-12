package com.z.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemUserMapper;
import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.param.system.UserQueryParam;
import com.z.admin.entity.po.system.SystemUser;
import com.z.admin.entity.vo.base.ResultCodeEnum;
import com.z.admin.entity.vo.system.UserLoginVo;
import com.z.admin.entity.vo.system.UserVo;
import com.z.admin.security.UserDetail;
import com.z.admin.service.ISystemUserService;
import com.z.admin.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author zhy
 * @description 系统用户
 * @date 2022/10/17
 */
@Service
public class SystemUserService extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService, UserDetailsService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public IPage<UserVo> query(UserQueryParam param) {
        IPage<SystemUser> iPageUser = this.page(param.getPage(), new QueryWrapper<>());
        return iPageUser.convert(UserVo::new);
    }

    @Override
    public UserLoginVo login(UserLoginForm form) {
        //创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken usernameAuthentication = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
        //认证
        Authentication authentication = this.authenticationManager.authenticate(usernameAuthentication);
        //获取自定义user
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        SystemUser systemUser = userDetail.getSystemUser();
        //构建vo
        UserLoginVo userLoginVo = systemUser.toVO(UserLoginVo.class);
        userLoginVo.setToken(JwtUtil.generate(systemUser.getUsername()));
        return userLoginVo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中查询出用户实体对象
        SystemUser user = this.getByUsername(username);
        // 若没查询到一定要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (user == null) {
            throw new UsernameNotFoundException(ResultCodeEnum.USER_NOT_EXIST.getValue());
        }
        // 走到这代表查询到了实体对象，那就返回我们自定义的UserDetail对象（这里权限暂时放个空集合）
        return new UserDetail(user, Collections.emptyList());
    }

    private SystemUser getByUsername(String username) {
        LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUser::getUsername, username);
        return this.getOne(wrapper);
    }
}

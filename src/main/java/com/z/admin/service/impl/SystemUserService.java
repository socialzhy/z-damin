package com.z.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemUserMapper;
import com.z.admin.entity.enums.ResultCodeEnum;
import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.param.system.UserQueryParam;
import com.z.admin.entity.po.SystemUser;
import com.z.admin.entity.vo.system.UserLoginVo;
import com.z.admin.entity.vo.system.UserVo;
import com.z.admin.exception.ServiceException;
import com.z.admin.security.UserDetail;
import com.z.admin.service.ISystemRolePermissionService;
import com.z.admin.service.ISystemUserPermissionService;
import com.z.admin.service.ISystemUserRoleService;
import com.z.admin.service.ISystemUserService;
import com.z.admin.util.JwtUtil;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhy
 * @description 系统用户
 * @date 2022/10/17
 */
@Service
public class SystemUserService extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService, UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private ISystemUserRoleService userRoleService;
    @Resource
    private ISystemRolePermissionService rolePermissionService;
    @Resource
    private ISystemUserPermissionService userPermissionService;

    @Override
    public IPage<UserVo> query(UserQueryParam param) {
        IPage<SystemUser> iPageUser = this.page(param.getPage(), new QueryWrapper<>());
        return iPageUser.convert(UserVo::new);
    }

    @Override
    public UserLoginVo login(UserLoginForm form) {
        // 根据用户名查询出用户实体对象
        SystemUser user = this.getByUsername(form.getUsername());
        // 若没有查到用户 或者 密码校验失败则抛出自定义异常
        if (user == null || !passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new ServiceException(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        // 需要返回给前端的VO对象
        UserLoginVo userLoginVo = user.toVO(UserLoginVo.class);
        // 生成JWT，将用户名数据存入其中
        userLoginVo.setToken(JwtUtil.generate(user.getUsername()));
        return userLoginVo;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // todo 待处理  改为缓存-> 数据库查询
        // 从数据库中查询出用户实体对象
        SystemUser user = this.getByUsername(username);
        // 没查询到需要抛出该异常，这样才能被Spring Security的错误处理器处理
        if (user == null) {
            throw new ServiceException(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (user.getIsDisabled()) {
            throw new ServiceException(ResultCodeEnum.USER_DISABLED);
        }

        //操作权限
        List<SimpleGrantedAuthority> operatePermissionList = new ArrayList<>();

        Long userId = user.getId();
        Set<Long> permissionIdSet = new HashSet<>();
        List<Long> roleIdList = this.userRoleService.queryRoleByUserId(userId);
        permissionIdSet.addAll(this.rolePermissionService.queryPermissionByRoleId(roleIdList));
        permissionIdSet.addAll(this.userPermissionService.queryPermissionByUserId(userId));
        for (Long permissionId : permissionIdSet) {
            operatePermissionList.add(new SimpleGrantedAuthority(permissionId.toString()));
        }

        // 认证成功，返回自定义的UserDetail对象
        return new UserDetail(user, operatePermissionList);
    }

    /**
     * 通过username查询用户
     */
    private SystemUser getByUsername(String username) {
        LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUser::getUsername, username);
        return this.getOne(wrapper);
    }
}

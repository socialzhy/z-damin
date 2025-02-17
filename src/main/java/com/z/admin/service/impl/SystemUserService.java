package com.z.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.z.admin.dao.SystemUserMapper;
import com.z.admin.entity.dto.UserLoginDto;
import com.z.admin.entity.enums.RedisKeyEnum;
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
import com.z.admin.util.*;
import jakarta.annotation.Resource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author zhy
 * @description 系统用户
 *
 *
 * //     * todo 待处理
 * //     * 权限分为直接指定用户的权限和用户关联角色的权限.
 * //     * <p>
 * //     * 修改用户权限后，更新缓存、更新数据库、注销用户登录状态
 * //     * 修改用户角色后，更新缓存、更新数据库、注销用户登录状态
 * //     * <p>
 * //     * 角色关联权限每次都通过缓存查询，修改时 更新缓存、更新数据
 */
@Service
public class SystemUserService extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private ISystemUserRoleService userRoleService;
    @Resource
    private ISystemRolePermissionService rolePermissionService;
    @Resource
    private ISystemUserPermissionService userPermissionService;
    @Resource
    private RedisUtil redisUtil;

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
        if (DataUtils.isEmpty(user) || !passwordEncoder.matches(form.getPassword(), user.getPassword())) {
            throw new ServiceException(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        // 如果用户已禁用
        if (user.getDisabled()) {
            throw new ServiceException(ResultCodeEnum.USER_DISABLED);
        }

        // 需要返回给前端的VO对象
        UserLoginVo userLoginVo = user.toVO(UserLoginVo.class);
        // 生成JWT，将用户名数据存入其中
        String token = JwtUtil.generate(user.getUsername());
        userLoginVo.setToken(token);

        // 查询权限
        Long userId = user.getId();
        List<Long> roleList = this.userRoleService.queryRoleByUserId(userId);
        List<Long> userPermissionList = this.userPermissionService.queryPermissionByUserId(userId);

        // 保存用户登录信息
        redisUtil.set(RedisKeyEnum.USER_INFO, user.getUsername(), UserLoginDto.of(user, roleList, userPermissionList, token));

        return userLoginVo;

    }

    @Override
    public UserDetail loadUserByCache(String username) {
        // 缓存查询
        UserLoginDto userLoginDto = redisUtil.getObjectFromRedis(RedisKeyEnum.USER_INFO, username, UserLoginDto.class);
        if (DataUtils.isEmpty(userLoginDto)) {
            return null;
        }
        List<SimpleGrantedAuthority> operatePermissionList = this.genSimpleGrantedAuthority(userLoginDto.getRoleList(), userLoginDto.getPermissionList());
        return new UserDetail(userLoginDto, operatePermissionList);
    }

    /**
     * 通过username查询用户
     */
    private SystemUser getByUsername(String username) {
        LambdaQueryWrapper<SystemUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SystemUser::getUsername, username);
        return this.getOne(wrapper);
    }

    /**
     * 构建权限
     *
     * @param roleList       用户的角色
     * @param permissionList 用户的指定权限
     */
    private List<SimpleGrantedAuthority> genSimpleGrantedAuthority(List<Long> roleList, List<Long> permissionList) {
        List<SimpleGrantedAuthority> operatePermissionList = new ArrayList<>();
        Set<Long> permissionIdSet = new HashSet<>();
        // todo 待处理 改缓存
        permissionIdSet.addAll(this.rolePermissionService.queryPermissionByRoleId(roleList));
        permissionIdSet.addAll(permissionList);
        for (Long permissionId : permissionIdSet) {
            operatePermissionList.add(new SimpleGrantedAuthority(permissionId.toString()));
        }

        return operatePermissionList;
    }

}

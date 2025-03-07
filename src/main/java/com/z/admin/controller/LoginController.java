package com.z.admin.controller;

import com.z.admin.entity.dto.UserLoginDto;
import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.vo.base.Result;
import com.z.admin.entity.vo.system.UserInfoVo;
import com.z.admin.entity.vo.system.UserLoginVo;
import com.z.admin.security.UserDetail;
import com.z.admin.service.ISystemUserService;
import com.z.admin.util.BeanUtils;
import com.z.admin.util.LoginUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author zhy
 * @description 登录相关
 */
@RestController
public class LoginController {

    @Resource
    ISystemUserService systemUserService;

    /**
     * 登录
     *
     * @param form 参数
     */
    @PostMapping("/login")
    public Result<UserLoginVo> login(@Valid @RequestBody UserLoginForm form) {
        return Result.success(systemUserService.login(form));
    }

    /**
     * 查询用户信息
     */
    @GetMapping("/info")
    public Result<UserInfoVo> info() {
        // 基本信息
        UserLoginDto userLoginDto = LoginUtil.getLoginUser().getUserLoginDto();
        UserInfoVo userInfoVo = BeanUtils.copyProperties(userLoginDto, UserInfoVo.class);

        // 权限处理
        UserDetail userDetail = LoginUtil.getLoginUser();
        Collection<GrantedAuthority> authorities = userDetail.getAuthorities();
        List<Long> permissionList = new ArrayList<>();
        for (GrantedAuthority authority : authorities) {
            permissionList.add(Long.parseLong(authority.getAuthority()));
        }
        userInfoVo.setPermissionList(permissionList);
        return Result.success(userInfoVo);
    }
}

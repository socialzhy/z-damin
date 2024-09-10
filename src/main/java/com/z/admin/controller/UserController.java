package com.z.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z.admin.entity.form.system.UserAddForm;
import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.form.system.UserUpdateForm;
import com.z.admin.entity.param.system.UserQueryParam;
import com.z.admin.entity.po.SystemUser;
import com.z.admin.entity.vo.base.BaseVo;
import com.z.admin.entity.vo.base.Result;
import com.z.admin.entity.vo.system.UserInfoVo;
import com.z.admin.entity.vo.system.UserLoginVo;
import com.z.admin.entity.vo.system.UserVo;
import com.z.admin.service.ISystemRoleService;
import com.z.admin.service.ISystemUserService;
import com.z.admin.util.LoginUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理
 *
 * @author zhy
 * @description 用户管理
 * @date 2022/10/17
 */
@RestController
@RequestMapping("/system/user")
public class UserController {
    @Resource
    ISystemUserService systemUserService;
    @Resource
    ISystemRoleService systemRoleService;

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
        SystemUser user = LoginUtil.getLoginUser().getSystemUser();


        UserInfoVo userInfoVo = user.toVO(UserInfoVo.class);
        //todo 待处理
//        userInfoVo.getPagePermissionList().add(1);
//        userInfoVo.getPagePermissionList().add(2);
        userInfoVo.getPagePermissionList().add(1);
        userInfoVo.getOperationPermissionList().add(235);
//        userInfoVo.getRoleList().add("qwert");
        return Result.success(userInfoVo);
    }

    /**
     * 新增用户
     *
     * @param form 用户信息
     */
    @PostMapping("/add")
    public Result<BaseVo> add(@RequestBody UserAddForm form) {
        SystemUser systemUser = form.toPo();
        systemUserService.save(systemUser);
        return Result.success(systemUser.toVO(BaseVo.class));
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     */
    @PostMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Long id) {
        systemUserService.removeById(id);
        return Result.success();
    }

    /**
     * 修改用户信息
     *
     * @param form 用户信息
     */
    @PostMapping("/update")
    public Result<?> update(@RequestBody UserUpdateForm form) {
        systemUserService.updateById(form.toPo());
        return Result.success();
    }

    /**
     * 用户详情
     *
     * @param id 用户id
     */
    @GetMapping("/{id}")
    public Result<UserInfoVo> detail(@PathVariable Long id) {
        SystemUser user = systemUserService.getById(id);
        return Result.success(user.toVO(UserInfoVo.class));
    }

    /**
     * 用户列表
     *
     * @param param 列表查询参数
     */
    @GetMapping("/list")
    public Result<IPage<UserVo>> list(@RequestBody UserQueryParam param) {
        return Result.success(systemUserService.query(param));
    }

}

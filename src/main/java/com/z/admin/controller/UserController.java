package com.z.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.z.admin.entity.form.system.UserAddForm;
import com.z.admin.entity.form.system.UserUpdateForm;
import com.z.admin.entity.param.system.UserQueryParam;
import com.z.admin.entity.po.system.SystemUser;
import com.z.admin.entity.vo.base.BaseVo;
import com.z.admin.entity.vo.base.Result;
import com.z.admin.entity.vo.system.UserDetailVo;
import com.z.admin.entity.vo.system.UserVo;
import com.z.admin.service.ISystemUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result<UserDetailVo> detail(@PathVariable Long id) {
        SystemUser user = systemUserService.getById(id);
        return Result.success(user.toVO(UserDetailVo.class));
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

package com.z.admin.controller;

import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.po.system.SystemUser;
import com.z.admin.entity.vo.base.Result;
import com.z.admin.entity.vo.system.UserDetailVo;
import com.z.admin.entity.vo.system.UserLoginVo;
import com.z.admin.service.ISystemUserService;
import com.z.admin.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * @author zhy
 * @description
 * @date 2022/9/21
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ISystemUserService userService;
    @Autowired
    private ISystemUserService systemUserService;

    @PostMapping("/xx")
    public void test(@RequestParam("name") String name, HttpSession session) throws Exception {
        System.out.println(session.getAttribute("tok"));
        System.out.println(name);
        session.setAttribute("tok", "12312131");
    }

    @PostMapping("/yy")
    public void yy(@Valid @RequestBody UserLoginForm form) throws Exception {
        System.out.println(form);
    }

    @PostMapping("/zz")
    public Result<?> zz() throws Exception {
        return Result.success();
    }


    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody UserLoginForm form) throws Exception {
        SystemUser user = new SystemUser();
        // 调用加密器将前端传递过来的密码进行加密
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        // 将用户实体对象添加到数据库
        userService.save(user);
        return Result.success();
    }




}

package com.z.admin.controller;

import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.po.SystemRole;
import com.z.admin.entity.po.SystemUser;
import com.z.admin.entity.vo.base.Result;
import com.z.admin.service.ISystemUserService;
import com.z.admin.util.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhy
 * @description
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private ISystemUserService userService;
    @Resource
    private RedisUtil redisUtil;

    @PostMapping("/xx")
    public void test(@RequestParam("name") String name, HttpSession session) {
        System.out.println(session.getAttribute("tok"));
        System.out.println(name);
        session.setAttribute("tok", "12312131");
    }

    @PostMapping("/yy")
    public void yy(@Valid @RequestBody UserLoginForm form) {
        System.out.println(form);
    }

    @PostMapping("/zz")
    public Result<?> zz() {
        return Result.success();
    }


    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody UserLoginForm form) {
        SystemUser user = new SystemUser();
        // 调用加密器将前端传递过来的密码进行加密
        user.setUsername(form.getUsername());
        user.setPassword(passwordEncoder.encode(form.getPassword()));
        // 将用户实体对象添加到数据库
        userService.save(user);
        return Result.success();
    }

    @GetMapping("/test")
    public void test() {
        Thread.ofVirtual().name("vittualThread").unstarted(() -> System.out.println("task run!!!")).start();
        System.out.println("success!!!");
    }
}

package com.z.admin.controller;

import com.z.admin.entity.form.system.UserLoginForm;
import com.z.admin.entity.vo.base.Result;
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

}

package com.z.admin.security;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.z.admin.entity.vo.base.Result;
import com.z.admin.entity.vo.base.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhy
 * @description
 * @date 2022/12/21
 */
public class MyEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 直接提示前端认证错误
        out.write(new ObjectMapper().writeValueAsString(Result.fail(ResultCodeEnum.AUTHENTICATION_FAILED)));
        out.flush();
        out.close();

    }
}

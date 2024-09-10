package com.z.admin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.z.admin.entity.enums.ResultCodeEnum;
import com.z.admin.entity.vo.base.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhy
 * @description 认证失败处理器
 * @date 2022/12/21
 */
public class MyEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 直接提示前端认证错误
        out.write(new ObjectMapper().writeValueAsString(Result.fail(ResultCodeEnum.AUTHENTICATION_FAILED)));
        out.flush();
        out.close();
    }
}

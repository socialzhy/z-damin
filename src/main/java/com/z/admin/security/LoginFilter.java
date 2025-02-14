//package com.z.admin.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.z.admin.entity.dto.UserLoginDto;
//import com.z.admin.entity.enums.RedisKeyEnum;
//import com.z.admin.entity.enums.ResultCodeEnum;
//import com.z.admin.entity.form.system.UserLoginForm;
//import com.z.admin.entity.vo.base.Result;
//import com.z.admin.entity.vo.system.UserLoginVo;
//import com.z.admin.exception.ServiceException;
//import com.z.admin.util.BeanUtils;
//import com.z.admin.util.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @author zhy
// * @description
// */
//public class LoginFilter extends UsernamePasswordAuthenticationFilter {
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            UserLoginForm userLoginForm = new ObjectMapper().readValue(request.getInputStream(), UserLoginForm.class);
//
//            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(userLoginForm.getUsername(), userLoginForm.getPassword());
//            setDetails(request, authRequest);
//            return this.getAuthenticationManager().authenticate(authRequest);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
//        // authResult获取自定义UserDetail
//        // 查询权限、存用户数据和权限数据
//        // 直接返回数据
//
//
//        UserDetail user = (UserDetail)authResult.getPrincipal();
//        response.setContentType("application/json;charset=utf-8");
//        PrintWriter out = response.getWriter();
//        // 直接提示前端认证错误
//        out.write(new ObjectMapper().writeValueAsString(Result.success(userLoginVo)));
//        out.flush();
//        out.close();
//    }
//}


// 通过spring security 的方式进行登录
// 继承UserDetailsService  实现loadUserByUsername方法 返回的UserDetail中需要有password字段
// 使用登录
// UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(form.getUsername(), form.getPassword());
// authenticationManager.authenticate(authToken);   这个方法会调用实现loadUserByUsername方法
// 可以配置登录uri， 走到这个类里

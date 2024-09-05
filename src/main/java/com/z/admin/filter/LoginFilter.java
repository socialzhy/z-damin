package com.z.admin.filter;

import com.z.admin.service.impl.SystemUserService;
import com.z.admin.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author zhy
 * @description 登录过滤器
 * @date 2022/12/21
 */
@Component
public class LoginFilter extends OncePerRequestFilter {
    @Resource
    private SystemUserService systemUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain chain) throws ServletException, IOException {
        // 从请求头中获取token字符串并解析
        Claims claims = JwtUtil.parse(request.getHeader("Authorization"));
        if (claims != null) {
            // 从`JWT`中提取出之前存储好的用户名
            String username = claims.getSubject();
            // 查询出用户对象
            UserDetails user = systemUserService.loadUserByUsername(username);
            // 手动组装一个认证对象
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
            // 将认证对象放到上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }
}
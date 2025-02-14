package com.z.admin.security;

import com.z.admin.service.impl.SystemUserService;
import com.z.admin.util.DataUtils;
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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @author zhy
 * @description 登录过滤器
 */
@Component
public class AuthFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    @Resource
    private SystemUserService systemUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull FilterChain chain) throws ServletException, IOException {
        // 从请求头中获取token字符串并解析
        String token = request.getHeader(AUTHORIZATION);
        Claims claims = JwtUtil.parse(token);
        if (claims != null) {
            // 从`JWT`中提取出之前存储好的用户名
            String username = claims.getSubject();
            // 从缓存中查询出用户对象,如果不在缓存里则认证失败,需要重新登录
            UserDetail user = systemUserService.loadUserByCache(username);
            if (!DataUtils.isEmpty(user) && token.equals(user.getUserLoginDto().getToken())) {
                // 手动组装一个认证对象
                Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
                // 将认证对象放到上下文中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

}
package com.z.admin.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author zhy
 * @description 请求匹配
 */
public class CombinedRequestMatcher implements RequestMatcher {

    private final AntPathRequestMatcher pathMatcher;
    private final HttpMethod httpMethod;

    public CombinedRequestMatcher(HttpMethod method, String path) {
        this.pathMatcher = new AntPathRequestMatcher(path);
        this.httpMethod = method;
    }


    @Override
    public boolean matches(HttpServletRequest request) {
        return pathMatcher.matches(request) && httpMethod.matches(request.getMethod());
    }
}

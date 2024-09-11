package com.z.admin.security;

import com.z.admin.service.ISystemPermissionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import java.util.List;

/**
 * @author zhy
 * @description 自定义匿名鉴权过滤器
 */
public class MyAnonymousAuthenticationFilter extends AnonymousAuthenticationFilter {

    private final static String ANONYMOUS_KEY = "anonymous";
    private final static String ANONYMOUS_USER_KEY = "anonymousUser";
    private final ISystemPermissionService permissionService;

    public MyAnonymousAuthenticationFilter(ISystemPermissionService permissionService) {
        super(ANONYMOUS_KEY);
        this.permissionService = permissionService;
    }

    @Override
    protected Authentication createAuthentication(HttpServletRequest request) {
        // 设置匿名访问权限 todo 待处理   改成缓存
        List<String> anonymousPermissionList = this.permissionService.queryAnonymousPermission();
        return new AnonymousAuthenticationToken(ANONYMOUS_KEY, ANONYMOUS_USER_KEY, AuthorityUtils.createAuthorityList(anonymousPermissionList));
    }
}

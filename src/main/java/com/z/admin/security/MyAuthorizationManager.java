package com.z.admin.security;

import com.z.admin.entity.po.SystemPermission;
import com.z.admin.service.ISystemPermissionService;
import com.z.admin.util.DataUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author zhy
 * @description
 */
@Configuration
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    /**
     * 超级管理员权限
     */
    private final String SUPPER_ADMIN_AUTH = "-1";

    @Resource
    private ISystemPermissionService permissionService;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        // 获取用户全部权限
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();

        // 超级管理员直接放行
        boolean superAdmin = authorities.stream().anyMatch(authority -> authority.getAuthority().equals(SUPPER_ADMIN_AUTH));
        if (superAdmin){
            return new AuthorizationDecision(true);
        }

        boolean granted = this.checkPermissions(authentication.get(),object.getRequest());

        return new AuthorizationDecision(granted);
    }

    /**
     * 校验是否用权限
     */
    private boolean checkPermissions(Authentication authentication, HttpServletRequest request) {
        List<SystemPermission> systemPermissionList = this.permissionService.queryOperationalPermission();

        SystemPermission permission = systemPermissionList.stream().filter(systemPermission -> this.checkRequest(request,systemPermission)).findFirst().orElse(null);
        if (DataUtils.isEmpty(permission)){
            return false;
        }

        return authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(permission.getId().toString()));
    }

    /**
     * 校验request是否匹配 todo 待处理  使用缓存
     */
    public boolean checkRequest(HttpServletRequest request,SystemPermission permission) {
        CombinedRequestMatcher matcher = new CombinedRequestMatcher(HttpMethod.valueOf(permission.getMethod()), permission.getPath());
        return matcher.matches(request);
    }
}

package com.z.admin.security;

import com.z.admin.entity.enums.RedisKeyEnum;
import com.z.admin.entity.enums.SystemPermissionLevel;
import com.z.admin.entity.po.SystemPermission;
import com.z.admin.util.DataUtils;
import com.z.admin.util.RedisUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author zhy
 * @description 自定义鉴权manager
 */
@Configuration
public class MyAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    /**
     * 超级管理员权限
     */
    private final String SUPPER_ADMIN_AUTH = "-1";

    @Resource
    private RedisUtil redisUtil;

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        // 获取用户全部权限
        Collection<? extends GrantedAuthority> authorities = authentication.get().getAuthorities();

        // 超级管理员直接放行
        boolean superAdmin = authorities.stream().anyMatch(authority -> authority.getAuthority().equals(SUPPER_ADMIN_AUTH));
        if (superAdmin) {
            return new AuthorizationDecision(true);
        }

        // 校验权限
        boolean granted = this.checkPermissions(authentication.get(), object.getRequest());

        return new AuthorizationDecision(granted);
    }

    /**
     * 校验是否有权限
     */
    private boolean checkPermissions(Authentication authentication, HttpServletRequest request) {
        // 获取所有的操作权限
        List<SystemPermission> systemPermissionList = this.redisUtil.lGetAll(RedisKeyEnum.PERMISSION, SystemPermission.class);

        // 校验访问资源是否存在
        SystemPermission permission = systemPermissionList.stream().filter(systemPermission -> this.checkRequest(request, HttpMethod.valueOf(systemPermission.getMethod()), systemPermission.getPath())).findFirst().orElse(null);
        if (DataUtils.isEmpty(permission)) {
            return false;
        }

        // 校验登录权限
        if (permission.getAccessLevel().equals(SystemPermissionLevel.LOGIN.getId()) && !(authentication instanceof AnonymousAuthenticationToken)) {
            return true;
        }

        // 鉴权
        return authentication.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(permission.getId().toString()));
    }

    /**
     * 校验request是否匹配
     */
    public boolean checkRequest(HttpServletRequest request, HttpMethod method, String path) {
        CombinedRequestMatcher matcher = new CombinedRequestMatcher(method, path);
        return matcher.matches(request);
    }
}

package com.z.admin.config;

import com.z.admin.security.UserDetail;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.util.function.Supplier;

/**
 * @author zhy
 * @description
 * @date 2023/12/12
 */
@Configuration
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {
    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        System.out.println("url : " + object.getRequest().getRequestURL());
        System.out.println("uri : " + object.getRequest().getRequestURI());
        System.out.println("method : " + object.getRequest().getMethod());
        UserDetail userDetail = (UserDetail) authentication.get().getPrincipal();
        System.out.println("principal : " + userDetail.getPassword());
        return new AuthorizationDecision(true);
    }
}

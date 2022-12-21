//package com.z.admin.config;
//
//import lombok.Getter;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityMetadataSource;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//
//
///**
// * @author zhy
// * @description
// * @date 2022/12/21
// */
//public class MySecurityMetadataSource implements SecurityMetadataSource {
//
//    /**
//     * 当前系统所有url资源
//     */
//    @Getter
//    private static final Set<Resource> RESOURCES = new HashSet<>();
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
//        // 该对象是Spring Security帮我们封装好的，可以通过该对象获取request等信息
//        FilterInvocation filterInvocation = (FilterInvocation) object;
//        HttpServletRequest request = filterInvocation.getRequest();
//        // 遍历所有权限资源，以和当前请求进行匹配
//        for (Resource resource : RESOURCES) {
//            // 因为我们url资源是这种格式：GET:/API/user/test/{id}，冒号前面是请求方法，冒号后面是请求路径，所以要字符串拆分
//            String[] split = resource.getPath().split(":");
//            // 因为/API/user/test/{id}这种路径参数不能直接equals来判断请求路径是否匹配，所以需要用Ant类来匹配
//            AntPathRequestMatcher ant = new AntPathRequestMatcher(split[1]);
//            // 如果请求方法和请求路径都匹配上了，则代表找到了这个请求所需的权限资源
//            if (request.getMethod().equals(split[0]) && ant.matches(request)) {
//                // 将我们权限资源id返回，这个SecurityConfig就是ConfigAttribute一个简单实现
//                return Collections.singletonList(new SecurityConfig(resource.getId().toString()));
//            }
//        }
//        // 走到这里就代表该请求无需授权即可访问，返回空
//        return null;
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return false;
//    }
//}

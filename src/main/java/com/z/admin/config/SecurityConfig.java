package com.z.admin.config;

import com.z.admin.entity.po.SystemPermission;
import com.z.admin.filter.LoginFilter;
import com.z.admin.security.MyEntryPoint;
import com.z.admin.service.ISystemPermissionService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private LoginFilter loginFilter;
    @Resource
    private ISystemPermissionService permissionService;

    /** 超级管理员权限 */
    private final String SUPPER_ADMIN_AUTH = "-1";

    // 配置 SecurityFilterChain 代替 configure(HttpSecurity http)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 关闭csrf和frameOptions
        http.csrf(AbstractHttpConfigurer::disable);
        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        // 开启跨域以便前端调用接口
        http.cors(cors -> cors.configurationSource(corsConfigurationSource())); // 自定义 CORS 配置

        // 关闭session管理
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // 将自定义的认证过滤器插入到默认的认证过滤器之前
        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

        // 配置认证规则
        http.authorizeHttpRequests(authorize -> {
                    // 放行登录和注册接口（以及其他不需要认证的接口）
                    authorize.requestMatchers("/system/user/login", "/test/register").permitAll()
                            // 允许预检请求
                            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll();

                    // 只要登录就可以访问的接口
                    authorize.requestMatchers("/system/user/info").authenticated();

                    // 允许超级管理员访问所有路径
                    authorize.requestMatchers("/**").hasAuthority(SUPPER_ADMIN_AUTH);

                    // 动态加载自定义权限配置
                    List<SystemPermission> permissionList = permissionService.queryOperationalPermission();
                    for (SystemPermission permission : permissionList) {
                        authorize.requestMatchers(permission.getPath())
                                .hasAuthority(permission.getId().toString());
                    }

                    // 其他请求都需要认证
                    authorize.anyRequest().authenticated();
                })
                // 指定认证错误处理器
                .exceptionHandling(exception -> exception.authenticationEntryPoint(new MyEntryPoint()));

        return http.build();
    }

    // 自定义 CORS 配置
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));  // 允许所有来源
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 允许的 HTTP 方法
        configuration.setAllowedHeaders(List.of("*")); // 允许的请求头

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 对所有路径生效
        return source;
    }

    // 密码加密器配置
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 使用security的AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

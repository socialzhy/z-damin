package com.z.admin.config;

import com.z.admin.filter.LoginFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhy
 * @description springSecurity配置
 * @date 2022/12/21
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    LoginFilter loginFilter;
    @Resource
    AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 关闭csrf防护
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(header -> header.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                // 开启跨域以便前端调用接口
                .cors(Customizer.withDefaults())
                //关闭session
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 将我们自定义的认证过滤器插入到默认的认证过滤器之前
                .addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);

        http.authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                // 允许所有OPTIONS请求
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // 允许直接访问授权登录接口
                .requestMatchers(HttpMethod.POST, "/system/user/login", "/test/register").permitAll()
                // 这里意思是其它所有接口需要认证才能访问
//                .anyRequest().authenticated()
                .anyRequest().access(authorizationManager));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AccessDecisionVoter<FilterInvocation> accessDecisionProcessor() {
//        return new AccessDecisionProcessor();
//    }
//
//    @Bean
//    public AccessDecisionManager accessDecisionManager() {
//        // 构造一个新的AccessDecisionManager 放入两个投票器
//        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(new WebExpressionVoter(), accessDecisionProcessor());
//        return new UnanimousBased(decisionVoters);
//    }

}

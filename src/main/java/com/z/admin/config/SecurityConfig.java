package com.z.admin.config;

import com.z.admin.filter.LoginFilter;
import com.z.admin.security.MyEntryPoint;
import com.z.admin.service.impl.SystemUserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsUtils;

/**
 * @author zhy
 * @description
 * @date 2022/12/21
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    SystemUserService userDetailsService;
    @Resource
    LoginFilter loginFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
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
                .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer.authenticationEntryPoint(new MyEntryPoint()));



        return http.build();
    }


//        @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // 关闭csrf和frameOptions，如果不关闭会影响前端请求接口（这里不展开细讲了，感兴趣的自行了解）
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//        // 开启跨域以便前端调用接口
//        http.cors();
//        //关闭session
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        // 将我们自定义的认证过滤器插入到默认的认证过滤器之前
//        http.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class);
//
//        // 这是配置的关键，决定哪些接口开启防护，哪些接口绕过防护
//        http.authorizeRequests()
//                // 注意这里，是允许前端跨域联调的一个必要配置
//                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
//                // 指定某些接口不需要通过验证即可访问。登陆、注册接口肯定是不需要认证的
//                .antMatchers("/system/user/login", "/test/register").permitAll()
//                // 这里意思是其它所有接口需要认证才能访问
//                .anyRequest().authenticated()
//                // 指定认证错误处理器
//                .and().exceptionHandling().authenticationEntryPoint(new MyEntryPoint());
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // DaoAuthenticationProvider 从自定义的 userDetailsService.loadUserByUsername 方法获取UserDetails
        authProvider.setUserDetailsService(userDetailsService);
        // 设置密码编辑器
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;

    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 指定UserDetailService和加密器
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

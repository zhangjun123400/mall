package com.zhangjun.mall.config;

import com.zhangjun.mall.filter.JwtAuthenticationTokenFilter;
import com.zhangjun.mall.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangjun
 * @Date 2025/4/28 16:59
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    //认证用户无权限访问资源的处理器
    @Autowired
    private CustomerAccessDeniedHandler customerAccessDeniedHandler;

    //客户端进行认证数据的提交时出现异常，或者是匿名用户访问受限资源的处理器
    @Autowired
    private AnonymousAuthenticationHandler anonymousAuthenticationHandler;

    //用户认证校验失败处理器
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    //白名单
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;

    @Autowired(required = false)
    private DynamicSecurityFilter dynamicSecurityFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 登陆时需要调用AuthenticationManager.authenticate执行一次校验
     * @param config
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    //登陆请求放行

    /**
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //配置关闭csrf机制
        http.csrf(AbstractHttpConfigurer::disable);

        //用户认证校验失败处理器loginFailureHandler
        http.formLogin(configure -> configure.failureHandler(loginFailureHandler));

        //STATELESS(无状态)：表示应用层程序是无状态的，不会创建会话
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        List<String> ignordList=ignoreUrlsConfig.getUrls();
        String[] whiteList = ignordList.toArray(new String[0]);
        /**
        //配置请求的拦截方式
        http.authorizeHttpRequests(auth ->
            auth.requestMatchers(HttpMethod.OPTIONS).permitAll() // 放行预检请求
                    .requestMatchers(whiteList)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
        );
         */
        http.logout(logout -> logout
                        .logoutUrl("/admin/logout") // 明确指定登出路径
                        .logoutSuccessHandler((request, response, authentication) -> {
                            // 登出成功后返回固定响应，避免重定向
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write("{\"code\":200, \"message\":\"登出成功\"}");
                        })
                        .permitAll() // 允许匿名访问登出端点
                )
             .authorizeHttpRequests(auth ->
                        auth.requestMatchers(HttpMethod.OPTIONS)
                                .permitAll() // 放行预检请求
                                .requestMatchers(whiteList)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                );

        //把token校验过滤器添加到过滤器链中
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        //添加自定义的异常处理器
        http.exceptionHandling(exception -> {
            exception.accessDeniedHandler(customerAccessDeniedHandler)
                     .authenticationEntryPoint(anonymousAuthenticationHandler);
        });

        //有动态权限配置时添加动态权限校验过滤器
        if (dynamicSecurityService != null){
            http.addFilterBefore(dynamicSecurityFilter, AuthorizationFilter.class);
        }
        return http.build();
    }



}

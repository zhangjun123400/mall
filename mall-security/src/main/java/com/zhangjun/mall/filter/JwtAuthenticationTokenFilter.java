package com.zhangjun.mall.filter;

import com.alibaba.fastjson2.JSON;
import com.zhangjun.mall.utils.JwtUtil;
import com.zhangjun.mall.config.IgnoreUrlsConfig;
import com.zhangjun.mall.exception.CustomerAuthenticationException;
import com.zhangjun.mall.handler.LoginFailureHandler;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/29 02:11
 * @Version 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String uri = request.getRequestURI();
            List<String> ignordList=ignoreUrlsConfig.getUrls();

            /**
             * ///判断如果是登陆接口
                if (!uri.equals("/user/login")) {
             this.validateToken(request);
                }
             */


            // 初始化路径匹配器
            AntPathMatcher pathMatcher = new AntPathMatcher();

            // 判断 URI 是否匹配任意白名单模式
            boolean isIgnored = ignordList.stream()
                    .anyMatch(pattern -> pathMatcher.match(pattern, uri));

            //判断如果是安全路径白名单
            if (!isIgnored) {

                this.validateToken(request);
            }
        } catch (AuthenticationException e) {
           // throw new RuntimeException(e);
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        }

        //放行
        filterChain.doFilter(request, response);
    }

    /**
     * 用于token校验
     * @param request
     */
    private void validateToken(HttpServletRequest request) {

        //Head中无token
        String token = request.getHeader("Authorization");

        if(ObjectUtils.isEmpty(token))
        {
            token = request.getParameter("Authorization");
        }

        if(ObjectUtils.isEmpty(token))
        {
           throw new CustomerAuthenticationException("token为空");
        }
        //redis进行校验
        Object redisStr = redisTemplate.opsForValue().get("token_"+token);

        if (ObjectUtils.isEmpty(redisStr)) {
            throw new CustomerAuthenticationException("token已经过期");
        }


        UserDetails loginUser = null;
        //校验令牌
        try {
            Claims claims =jwtUtil.parseJWT(token);
            String subject = claims.getSubject();
            //把字符串专程loginUser对象
            loginUser =JSON.parseObject(subject, loginUser.getClass());
        } catch (Exception e) {
            throw new CustomerAuthenticationException("token校验失败");
        }

        //把验证完获取到的用户信息在此放入springsecurity的上下文中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }
}

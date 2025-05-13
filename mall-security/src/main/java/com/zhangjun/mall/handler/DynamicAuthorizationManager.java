package com.zhangjun.mall.handler;

import cn.hutool.core.collection.CollUtil;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * @Author zhangjun
 * @Date 2025/5/14 02:09
 * @Version 1.0
 */
@Component
public class DynamicAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authenticationSupplier,
                                       RequestAuthorizationContext context) {
        // 从请求上下文中获取动态权限配置（需配合自定义的 SecurityMetadataSource）
        Collection<String> requiredAuthorities = (Collection<String>) context.getRequest().getAttribute("DYNAMIC_AUTHORITIES");

        // 无权限配置则直接放行
        if (CollUtil.isEmpty(requiredAuthorities)) {
            return new AuthorizationDecision(true);
        }

        Authentication authentication = authenticationSupplier.get();
        if (authentication == null || !authentication.isAuthenticated()) {
            return new AuthorizationDecision(false);
        }

        // 检查用户权限是否满足需求
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (requiredAuthorities.contains(authority.getAuthority())) {
                return new AuthorizationDecision(true);
            }
        }

        return new AuthorizationDecision(false);
    }
}

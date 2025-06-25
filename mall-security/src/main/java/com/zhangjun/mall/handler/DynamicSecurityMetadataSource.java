package com.zhangjun.mall.handler;

import cn.hutool.core.util.URLUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

/**
 * @Author zhangjun
 * @Date 2025/5/21 21:59
 * @Version 1.0
 */
@ConditionalOnBean(name = "dynamicSecurityService")
@Component
public class DynamicSecurityMetadataSource  implements FilterInvocationSecurityMetadataSource {

    private static Map<String,ConfigAttribute> configAttributeMap =null;

    @Autowired
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource(){
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource(){
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (configAttributeMap == null) this.loadDataSource();
        List<ConfigAttribute> configAttributes = new ArrayList<>();

        //获取当前访问的路径
        String url = ((FilterInvocation)object).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        //获取访问该路径所需资源
        for (String pattern : configAttributeMap.keySet()) {
            if (pathMatcher.match(pattern, url)) {
                configAttributes.add(configAttributeMap.get(pattern));
            }
        }

        //未设置操作请求权限，返回空集合
        return configAttributes;

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

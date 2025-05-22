package com.zhangjun.mall.handler;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @Author zhangjun
 * @Date 2025/5/21 21:58
 * @Version 1.0
 */
public interface DynamicSecurityService {

    /**
     * 加载资源ANT通配符和资源对应MAP
     * @return
     */
    Map<String, ConfigAttribute> loadDataSource();
}

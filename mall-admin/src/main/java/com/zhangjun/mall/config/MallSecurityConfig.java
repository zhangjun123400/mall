package com.zhangjun.mall.config;

import cn.hutool.core.collection.ListUtil;
import com.zhangjun.mall.handler.DynamicSecurityService;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.service.UmsAdminService;
import com.zhangjun.mall.service.UmsResourceService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * mall-security模块相关配置
 * Created by macro on 2019/11/5.
 */
@Configuration
public class MallSecurityConfig{

    @Autowired
    private UmsResourceService umsResourceService;


    @Bean
    public DynamicSecurityService dynamicSecurityService(){
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String,ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResource> resourceList = umsResourceService.listAll();
                for (UmsResource umsResource : resourceList){
                    map.put(umsResource.getUrl(),new org.springframework.security.access.SecurityConfig(umsResource.getId()+":"+umsResource.getName()));
                }
                return map;
            }
        };
    }
}

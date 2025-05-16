package com.zhangjun.mall.config;

import com.zhangjun.common.config.BaseSwaggerConfig;
import com.zhangjun.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @Author zhangjun
 * @Date 2025/5/14 22:10
 * @Version 1.0
 */
@Configuration
@Profile({"dev", "test"}) // 只在开发/测试环境开启
public class OpenApiConfig extends BaseSwaggerConfig {
//    @Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("电商系统后台API文档")
//                        .version("1.0.0")
//                        .description("Mall在线商城后台接口文档")
//                        .contact(new Contact()
//                                .name("zhangjun")
//                                .url("http://localhost:8090/swagger-ui/index.html#/")
//                                .email("support@example.com"))
//                        .license(new License()
//                                .name("Apache 2.0")
//                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
//                );
//    }

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.zhangjun.mall.controller")
                .title("电商系统后台API文档")
                .version("1.1.0")
                .description("Mall在线商城后台接口文档")
                .contactName("zhangjun")
                .contactEmail("support@example.com")
                .enableSecurity(true)
                .build();
    }
}

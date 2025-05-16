package com.zhangjun.common.config;

import com.zhangjun.common.domain.SwaggerProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger基础配置（升级为SpringDoc OpenAPI 3）
 */

public abstract class BaseSwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        SwaggerProperties swaggerProperties = swaggerProperties();

        // 创建 OpenAPI 实例
        OpenAPI openAPI = new OpenAPI()
                .info(new Info()
                        .title(swaggerProperties.getTitle())
                        .description(swaggerProperties.getDescription())
                        .contact(new Contact()
                                .name(swaggerProperties.getContactName())
                                .url(swaggerProperties.getContactUrl())
                                .email(swaggerProperties.getContactEmail()))
                        .version(swaggerProperties.getVersion()));

        // 如果启用了安全配置，则添加安全方案
        if (swaggerProperties.isEnableSecurity()) {
            openAPI.components(new Components()
                    .addSecuritySchemes("Authorization", new SecurityScheme()
                            .type(SecurityScheme.Type.APIKEY)
                            .in(SecurityScheme.In.HEADER)
                            .name("Authorization")));

            // 添加全局安全要求
            List<SecurityRequirement> securityRequirements = new ArrayList<>();
            SecurityRequirement securityRequirement = new SecurityRequirement();
            securityRequirement.addList("Authorization");
            securityRequirements.add(securityRequirement);

            openAPI.security(securityRequirements);
        }

        return openAPI;
    }

    /**
     * 自定义Swagger配置
     */
    public abstract SwaggerProperties swaggerProperties();
}
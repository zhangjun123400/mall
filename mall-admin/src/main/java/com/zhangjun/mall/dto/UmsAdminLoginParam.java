package com.zhangjun.mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author zhangjun
 * @Date 2025/5/23 22:58
 * @Version 1.0
 */
@Data
@EqualsAndHashCode
public class UmsAdminLoginParam {

    @NotEmpty
    @Schema(description = "用户名", requiredMode =Schema.RequiredMode.REQUIRED)
    private String username;

    @NotEmpty
    @Schema(description = "密码", requiredMode =Schema.RequiredMode.REQUIRED)
    private String password;
}

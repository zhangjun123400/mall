package com.zhangjun.mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @Author zhangjun
 * @Date 2025/5/15 21:15
 * @Version 1.0
 */
@Data
public class UpdateAdminPasswordParam {
    @NotEmpty
    @Schema(description = "用户名", requiredMode =Schema.RequiredMode.REQUIRED)
    private String username;
    @NotEmpty
    @Schema(description = "旧密码",  requiredMode =Schema.RequiredMode.REQUIRED)
    private String oldPassword;
    @NotEmpty
    @Schema(description = "新密码",  requiredMode =Schema.RequiredMode.REQUIRED)
    private String newPassword;
}

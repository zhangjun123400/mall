package com.zhangjun.mall.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @Author zhangjun
 * @Date 2025/5/15 17:14
 * @Version 1.0
 */
@Data
public class UmsAdminParam {
    @NotEmpty
    @Schema(description = "用户名", requiredMode =Schema.RequiredMode.REQUIRED)
    private String username;
    @NotEmpty
    @Schema(description = "密码",  requiredMode =Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(description = "用户头像")
    private String icon;
    @Email
    @Schema(description = "邮箱")
    private String email;
    @Schema(description = "用户昵称")
    private String nickName;
    @Schema(description = "备注")
    private String note;
}

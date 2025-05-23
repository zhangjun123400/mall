package com.zhangjun.mall.dto;

import com.zhangjun.mall.model.UmsMenu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 23:02
 * @Version 1.0
 */
@Data
public class UmsMenuNode extends UmsMenu {
    @Schema(description = "子级菜单")
    private List<UmsMenuNode> children;
}

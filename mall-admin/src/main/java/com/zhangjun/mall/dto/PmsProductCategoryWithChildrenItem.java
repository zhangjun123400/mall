package com.zhangjun.mall.dto;

import com.zhangjun.mall.model.PmsProductCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/18 22:29
 * @Version 1.0
 */
public class PmsProductCategoryWithChildrenItem extends PmsProductCategory {
    @Getter
    @Setter
    @Schema(description = "子级分类")
    private List<PmsProductCategory> children;
}

package com.zhangjun.mall.dto;

import com.zhangjun.mall.model.PmsProductAttribute;
import com.zhangjun.mall.model.PmsProductAttributeCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 带有属性的商品属性分类
 * @Author zhangjun
 * @Date 2025/6/18 22:25
 * @Version 1.0
 */
public class PmsProductAttributeCategoryItem extends PmsProductAttributeCategory {
    @Getter
    @Setter
    @Schema(description = "商品属性列表")
    private List<PmsProductAttribute> productAttributeList;
}

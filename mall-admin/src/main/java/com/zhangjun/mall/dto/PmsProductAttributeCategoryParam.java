package com.zhangjun.mall.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 产品属性分类表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Data
@Schema(name = "PmsProductAttributeCategoryParam", description = "产品属性分类表")
public class PmsProductAttributeCategoryParam implements Serializable {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "属性数量")
    private Integer attributeCount;

    @Schema(description = "参数数量")
    private Integer paramCount;
}

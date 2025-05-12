package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 产品分类
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("pms_product_category")
@Schema(name = "PmsProductCategory", description = "产品分类")
public class PmsProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "上机分类的编号：0表示一级分类")
    @TableField("parent_id")
    private Long parentId;

    @TableField("name")
    private String name;

    @Schema(description = "分类级别：0->1级；1->2级")
    @TableField("level")
    private Integer level;

    @TableField("product_count")
    private Integer productCount;

    @TableField("product_unit")
    private String productUnit;

    @Schema(description = "是否显示在导航栏：0->不显示；1->显示")
    @TableField("nav_status")
    private Integer navStatus;

    @Schema(description = "显示状态：0->不显示；1->显示")
    @TableField("show_status")
    private Integer showStatus;

    @TableField("sort")
    private Integer sort;

    @Schema(description = "图标")
    @TableField("icon")
    private String icon;

    @TableField("keywords")
    private String keywords;

    @Schema(description = "描述")
    @TableField("description")
    private String description;
}

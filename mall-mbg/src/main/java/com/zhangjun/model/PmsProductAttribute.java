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
 * 商品属性参数表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("pms_product_attribute")
@Schema(name = "PmsProductAttribute", description = "商品属性参数表")
public class PmsProductAttribute implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_attribute_category_id")
    private Long productAttributeCategoryId;

    @TableField("name")
    private String name;

    @Schema(description = "属性选择类型：0->唯一；1->单选；2->多选")
    @TableField("select_type")
    private Integer selectType;

    @Schema(description = "属性录入方式：0->手工录入；1->从列表中选取")
    @TableField("input_type")
    private Integer inputType;

    @Schema(description = "可选值列表，以逗号隔开")
    @TableField("input_list")
    private String inputList;

    @Schema(description = "排序字段：最高的可以单独上传图片")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "分类筛选样式：1->普通；1->颜色")
    @TableField("filter_type")
    private Integer filterType;

    @Schema(description = "检索类型；0->不需要进行检索；1->关键字检索；2->范围检索")
    @TableField("search_type")
    private Integer searchType;

    @Schema(description = "相同属性产品是否关联；0->不关联；1->关联")
    @TableField("related_status")
    private Integer relatedStatus;

    @Schema(description = "是否支持手动新增；0->不支持；1->支持")
    @TableField("hand_add_status")
    private Integer handAddStatus;

    @Schema(description = "属性的类型；0->规格；1->参数")
    @TableField("type")
    private Integer type;
}

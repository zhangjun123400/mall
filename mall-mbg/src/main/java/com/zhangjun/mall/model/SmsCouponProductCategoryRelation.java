package com.zhangjun.mall.model;

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
 * 优惠券和产品分类关系表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_coupon_product_category_relation")
@Schema(name = "SmsCouponProductCategoryRelation", description = "优惠券和产品分类关系表")
public class SmsCouponProductCategoryRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("coupon_id")
    private Long couponId;

    @TableField("product_category_id")
    private Long productCategoryId;

    @Schema(description = "产品分类名称")
    @TableField("product_category_name")
    private String productCategoryName;

    @Schema(description = "父分类名称")
    @TableField("parent_category_name")
    private String parentCategoryName;
}

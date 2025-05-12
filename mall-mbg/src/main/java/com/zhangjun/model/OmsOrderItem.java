package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单中所包含的商品
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("oms_order_item")
@Schema(name = "OmsOrderItem", description = "订单中所包含的商品")
public class OmsOrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单id")
    @TableField("order_id")
    private Long orderId;

    @Schema(description = "订单编号")
    @TableField("order_sn")
    private String orderSn;

    @TableField("product_id")
    private Long productId;

    @TableField("product_pic")
    private String productPic;

    @TableField("product_name")
    private String productName;

    @TableField("product_brand")
    private String productBrand;

    @TableField("product_sn")
    private String productSn;

    @Schema(description = "销售价格")
    @TableField("product_price")
    private BigDecimal productPrice;

    @Schema(description = "购买数量")
    @TableField("product_quantity")
    private Integer productQuantity;

    @Schema(description = "商品sku编号")
    @TableField("product_sku_id")
    private Long productSkuId;

    @Schema(description = "商品sku条码")
    @TableField("product_sku_code")
    private String productSkuCode;

    @Schema(description = "商品分类id")
    @TableField("product_category_id")
    private Long productCategoryId;

    @Schema(description = "商品促销名称")
    @TableField("promotion_name")
    private String promotionName;

    @Schema(description = "商品促销分解金额")
    @TableField("promotion_amount")
    private BigDecimal promotionAmount;

    @Schema(description = "优惠券优惠分解金额")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;

    @Schema(description = "积分优惠分解金额")
    @TableField("integration_amount")
    private BigDecimal integrationAmount;

    @Schema(description = "该商品经过优惠后的分解金额")
    @TableField("real_amount")
    private BigDecimal realAmount;

    @TableField("gift_integration")
    private Integer giftIntegration;

    @TableField("gift_growth")
    private Integer giftGrowth;

    @Schema(description = "商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    @TableField("product_attr")
    private String productAttr;
}

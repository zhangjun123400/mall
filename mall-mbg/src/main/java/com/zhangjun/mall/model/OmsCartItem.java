package com.zhangjun.mall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 购物车表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("oms_cart_item")
@Schema(name = "OmsCartItem", description = "购物车表")
public class OmsCartItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @TableField("product_sku_id")
    private Long productSkuId;

    @TableField("member_id")
    private Long memberId;

    @Schema(description = "购买数量")
    @TableField("quantity")
    private Integer quantity;

    @Schema(description = "添加到购物车的价格")
    @TableField("price")
    private BigDecimal price;

    @Schema(description = "商品主图")
    @TableField("product_pic")
    private String productPic;

    @Schema(description = "商品名称")
    @TableField("product_name")
    private String productName;

    @Schema(description = "商品副标题（卖点）")
    @TableField("product_sub_title")
    private String productSubTitle;

    @Schema(description = "商品sku条码")
    @TableField("product_sku_code")
    private String productSkuCode;

    @Schema(description = "会员昵称")
    @TableField("member_nickname")
    private String memberNickname;

    @Schema(description = "创建时间")
    @TableField("create_date")
    private LocalDateTime createDate;

    @Schema(description = "修改时间")
    @TableField("modify_date")
    private LocalDateTime modifyDate;

    @Schema(description = "是否删除")
    @TableField("delete_status")
    private Integer deleteStatus;

    @Schema(description = "商品分类")
    @TableField("product_category_id")
    private Long productCategoryId;

    @TableField("product_brand")
    private String productBrand;

    @TableField("product_sn")
    private String productSn;

    @Schema(description = "商品销售属性:[{\"key\":\"颜色\",\"value\":\"颜色\"},{\"key\":\"容量\",\"value\":\"4G\"}]")
    @TableField("product_attr")
    private String productAttr;
}

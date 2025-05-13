package com.zhangjun.mall.model;

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
 * sku的库存
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("pms_sku_stock")
@Schema(name = "PmsSkuStock", description = "sku的库存")
public class PmsSkuStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @Schema(description = "sku编码")
    @TableField("sku_code")
    private String skuCode;

    @TableField("price")
    private BigDecimal price;

    @Schema(description = "库存")
    @TableField("stock")
    private Integer stock;

    @Schema(description = "预警库存")
    @TableField("low_stock")
    private Integer lowStock;

    @Schema(description = "展示图片")
    @TableField("pic")
    private String pic;

    @Schema(description = "销量")
    @TableField("sale")
    private Integer sale;

    @Schema(description = "单品促销价格")
    @TableField("promotion_price")
    private BigDecimal promotionPrice;

    @Schema(description = "锁定库存")
    @TableField("lock_stock")
    private Integer lockStock;

    @Schema(description = "商品销售属性，json格式")
    @TableField("sp_data")
    private String spData;
}

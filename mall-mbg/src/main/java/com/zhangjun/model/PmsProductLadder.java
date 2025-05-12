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
 * 产品阶梯价格表(只针对同商品)
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("pms_product_ladder")
@Schema(name = "PmsProductLadder", description = "产品阶梯价格表(只针对同商品)")
public class PmsProductLadder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("product_id")
    private Long productId;

    @Schema(description = "满足的商品数量")
    @TableField("count")
    private Integer count;

    @Schema(description = "折扣")
    @TableField("discount")
    private BigDecimal discount;

    @Schema(description = "折后价格")
    @TableField("price")
    private BigDecimal price;
}

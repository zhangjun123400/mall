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
 * 商品限时购与商品关系表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_flash_promotion_product_relation")
@Schema(name = "SmsFlashPromotionProductRelation", description = "商品限时购与商品关系表")
public class SmsFlashPromotionProductRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("flash_promotion_id")
    private Long flashPromotionId;

    @Schema(description = "编号")
    @TableField("flash_promotion_session_id")
    private Long flashPromotionSessionId;

    @TableField("product_id")
    private Long productId;

    @Schema(description = "限时购价格")
    @TableField("flash_promotion_price")
    private BigDecimal flashPromotionPrice;

    @Schema(description = "限时购数量")
    @TableField("flash_promotion_count")
    private Integer flashPromotionCount;

    @Schema(description = "每人限购数量")
    @TableField("flash_promotion_limit")
    private Integer flashPromotionLimit;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;
}

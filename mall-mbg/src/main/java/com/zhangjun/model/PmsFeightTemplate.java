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
 * 运费模版
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("pms_feight_template")
@Schema(name = "PmsFeightTemplate", description = "运费模版")
public class PmsFeightTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @Schema(description = "计费类型:0->按重量；1->按件数")
    @TableField("charge_type")
    private Integer chargeType;

    @Schema(description = "首重kg")
    @TableField("first_weight")
    private BigDecimal firstWeight;

    @Schema(description = "首费（元）")
    @TableField("first_fee")
    private BigDecimal firstFee;

    @TableField("continue_weight")
    private BigDecimal continueWeight;

    @TableField("continme_fee")
    private BigDecimal continmeFee;

    @Schema(description = "目的地（省、市）")
    @TableField("dest")
    private String dest;
}

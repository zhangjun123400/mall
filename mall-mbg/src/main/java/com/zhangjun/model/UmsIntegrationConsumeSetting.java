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
 * 积分消费设置
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_integration_consume_setting")
@Schema(name = "UmsIntegrationConsumeSetting", description = "积分消费设置")
public class UmsIntegrationConsumeSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "每一元需要抵扣的积分数量")
    @TableField("deduction_per_amount")
    private Integer deductionPerAmount;

    @Schema(description = "每笔订单最高抵用百分比")
    @TableField("max_percent_per_order")
    private Integer maxPercentPerOrder;

    @Schema(description = "每次使用积分最小单位100")
    @TableField("use_unit")
    private Integer useUnit;

    @Schema(description = "是否可以和优惠券同用；0->不可以；1->可以")
    @TableField("coupon_status")
    private Integer couponStatus;
}

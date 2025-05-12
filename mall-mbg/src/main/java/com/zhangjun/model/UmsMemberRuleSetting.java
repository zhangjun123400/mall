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
 * 会员积分成长规则表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_member_rule_setting")
@Schema(name = "UmsMemberRuleSetting", description = "会员积分成长规则表")
public class UmsMemberRuleSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "连续签到天数")
    @TableField("continue_sign_day")
    private Integer continueSignDay;

    @Schema(description = "连续签到赠送数量")
    @TableField("continue_sign_point")
    private Integer continueSignPoint;

    @Schema(description = "每消费多少元获取1个点")
    @TableField("consume_per_point")
    private BigDecimal consumePerPoint;

    @Schema(description = "最低获取点数的订单金额")
    @TableField("low_order_amount")
    private BigDecimal lowOrderAmount;

    @Schema(description = "每笔订单最高获取点数")
    @TableField("max_point_per_order")
    private Integer maxPointPerOrder;

    @Schema(description = "类型：0->积分规则；1->成长值规则")
    @TableField("type")
    private Integer type;
}

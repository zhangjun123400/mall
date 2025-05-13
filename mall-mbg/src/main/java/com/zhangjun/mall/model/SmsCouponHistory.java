package com.zhangjun.mall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 优惠券使用、领取历史表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_coupon_history")
@Schema(name = "SmsCouponHistory", description = "优惠券使用、领取历史表")
public class SmsCouponHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("coupon_id")
    private Long couponId;

    @TableField("member_id")
    private Long memberId;

    @TableField("coupon_code")
    private String couponCode;

    @Schema(description = "领取人昵称")
    @TableField("member_nickname")
    private String memberNickname;

    @Schema(description = "获取类型：0->后台赠送；1->主动获取")
    @TableField("get_type")
    private Integer getType;

    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "使用状态：0->未使用；1->已使用；2->已过期")
    @TableField("use_status")
    private Integer useStatus;

    @Schema(description = "使用时间")
    @TableField("use_time")
    private LocalDateTime useTime;

    @Schema(description = "订单编号")
    @TableField("order_id")
    private Long orderId;

    @Schema(description = "订单号码")
    @TableField("order_sn")
    private String orderSn;
}

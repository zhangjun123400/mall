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
 * 订单设置表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("oms_order_setting")
@Schema(name = "OmsOrderSetting", description = "订单设置表")
public class OmsOrderSetting implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "秒杀订单超时关闭时间(分)")
    @TableField("flash_order_overtime")
    private Integer flashOrderOvertime;

    @Schema(description = "正常订单超时时间(分)")
    @TableField("normal_order_overtime")
    private Integer normalOrderOvertime;

    @Schema(description = "发货后自动确认收货时间（天）")
    @TableField("confirm_overtime")
    private Integer confirmOvertime;

    @Schema(description = "自动完成交易时间，不能申请售后（天）")
    @TableField("finish_overtime")
    private Integer finishOvertime;

    @Schema(description = "订单完成后自动好评时间（天）")
    @TableField("comment_overtime")
    private Integer commentOvertime;
}

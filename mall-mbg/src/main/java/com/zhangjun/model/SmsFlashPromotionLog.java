package com.zhangjun.model;

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
 * 限时购通知记录
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_flash_promotion_log")
@Schema(name = "SmsFlashPromotionLog", description = "限时购通知记录")
public class SmsFlashPromotionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("member_id")
    private Integer memberId;

    @TableField("product_id")
    private Long productId;

    @TableField("member_phone")
    private String memberPhone;

    @TableField("product_name")
    private String productName;

    @Schema(description = "会员订阅时间")
    @TableField("subscribe_time")
    private LocalDateTime subscribeTime;

    @TableField("send_time")
    private LocalDateTime sendTime;
}

package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 限时购场次表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_flash_promotion_session")
@Schema(name = "SmsFlashPromotionSession", description = "限时购场次表")
public class SmsFlashPromotionSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "场次名称")
    @TableField("name")
    private String name;

    @Schema(description = "每日开始时间")
    @TableField("start_time")
    private LocalTime startTime;

    @Schema(description = "每日结束时间")
    @TableField("end_time")
    private LocalTime endTime;

    @Schema(description = "启用状态：0->不启用；1->启用")
    @TableField("status")
    private Integer status;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}

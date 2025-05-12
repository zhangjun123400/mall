package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 限时购表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_flash_promotion")
@Schema(name = "SmsFlashPromotion", description = "限时购表")
public class SmsFlashPromotion implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "秒杀时间段名称")
    @TableField("title")
    private String title;

    @Schema(description = "开始日期")
    @TableField("start_date")
    private LocalDate startDate;

    @Schema(description = "结束日期")
    @TableField("end_date")
    private LocalDate endDate;

    @Schema(description = "上下线状态")
    @TableField("status")
    private Integer status;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}

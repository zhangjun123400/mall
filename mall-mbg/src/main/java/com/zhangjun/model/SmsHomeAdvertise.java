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
 * 首页轮播广告表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_home_advertise")
@Schema(name = "SmsHomeAdvertise", description = "首页轮播广告表")
public class SmsHomeAdvertise implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @Schema(description = "轮播位置：0->PC首页轮播；1->app首页轮播")
    @TableField("type")
    private Integer type;

    @TableField("pic")
    private String pic;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema(description = "上下线状态：0->下线；1->上线")
    @TableField("status")
    private Integer status;

    @Schema(description = "点击数")
    @TableField("click_count")
    private Integer clickCount;

    @Schema(description = "下单数")
    @TableField("order_count")
    private Integer orderCount;

    @Schema(description = "链接地址")
    @TableField("url")
    private String url;

    @Schema(description = "备注")
    @TableField("note")
    private String note;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;
}

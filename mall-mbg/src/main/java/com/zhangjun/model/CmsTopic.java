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
 * 话题表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("cms_topic")
@Schema(name = "CmsTopic", description = "话题表")
public class CmsTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("category_id")
    private Long categoryId;

    @TableField("name")
    private String name;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("start_time")
    private LocalDateTime startTime;

    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema(description = "参与人数")
    @TableField("attend_count")
    private Integer attendCount;

    @Schema(description = "关注人数")
    @TableField("attention_count")
    private Integer attentionCount;

    @TableField("read_count")
    private Integer readCount;

    @Schema(description = "奖品名称")
    @TableField("award_name")
    private String awardName;

    @Schema(description = "参与方式")
    @TableField("attend_type")
    private String attendType;

    @Schema(description = "话题内容")
    @TableField("content")
    private String content;
}

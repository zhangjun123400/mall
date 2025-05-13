package com.zhangjun.mall.model;

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
 * 会员任务表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_member_task")
@Schema(name = "UmsMemberTask", description = "会员任务表")
public class UmsMemberTask implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @Schema(description = "赠送成长值")
    @TableField("growth")
    private Integer growth;

    @Schema(description = "赠送积分")
    @TableField("intergration")
    private Integer intergration;

    @Schema(description = "任务类型：0->新手任务；1->日常任务")
    @TableField("type")
    private Integer type;
}

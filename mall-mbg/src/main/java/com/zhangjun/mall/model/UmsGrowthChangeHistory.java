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
 * 成长值变化历史记录表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_growth_change_history")
@Schema(name = "UmsGrowthChangeHistory", description = "成长值变化历史记录表")
public class UmsGrowthChangeHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "改变类型：0->增加；1->减少")
    @TableField("change_type")
    private Integer changeType;

    @Schema(description = "积分改变数量")
    @TableField("change_count")
    private Integer changeCount;

    @Schema(description = "操作人员")
    @TableField("operate_man")
    private String operateMan;

    @Schema(description = "操作备注")
    @TableField("operate_note")
    private String operateNote;

    @Schema(description = "积分来源：0->购物；1->管理员修改")
    @TableField("source_type")
    private Integer sourceType;
}

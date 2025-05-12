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
 * 订单操作历史记录
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("oms_order_operate_history")
@Schema(name = "OmsOrderOperateHistory", description = "订单操作历史记录")
public class OmsOrderOperateHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单id")
    @TableField("order_id")
    private Long orderId;

    @Schema(description = "操作人：用户；系统；后台管理员")
    @TableField("operate_man")
    private String operateMan;

    @Schema(description = "操作时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    @TableField("order_status")
    private Integer orderStatus;

    @Schema(description = "备注")
    @TableField("note")
    private String note;
}

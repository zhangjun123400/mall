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
 * 退货原因表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("oms_order_return_reason")
@Schema(name = "OmsOrderReturnReason", description = "退货原因表")
public class OmsOrderReturnReason implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "退货类型")
    @TableField("name")
    private String name;

    @TableField("sort")
    private Integer sort;

    @Schema(description = "状态：0->不启用；1->启用")
    @TableField("status")
    private Integer status;

    @Schema(description = "添加时间")
    @TableField("create_time")
    private LocalDateTime createTime;
}

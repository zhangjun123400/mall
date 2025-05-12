package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 会员等级表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_member_level")
@Schema(name = "UmsMemberLevel", description = "会员等级表")
public class UmsMemberLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("growth_point")
    private Integer growthPoint;

    @Schema(description = "是否为默认等级：0->不是；1->是")
    @TableField("default_status")
    private Integer defaultStatus;

    @Schema(description = "免运费标准")
    @TableField("free_freight_point")
    private BigDecimal freeFreightPoint;

    @Schema(description = "每次评价获取的成长值")
    @TableField("comment_growth_point")
    private Integer commentGrowthPoint;

    @Schema(description = "是否有免邮特权")
    @TableField("priviledge_free_freight")
    private Integer priviledgeFreeFreight;

    @Schema(description = "是否有签到特权")
    @TableField("priviledge_sign_in")
    private Integer priviledgeSignIn;

    @Schema(description = "是否有评论获奖励特权")
    @TableField("priviledge_comment")
    private Integer priviledgeComment;

    @Schema(description = "是否有专享活动特权")
    @TableField("priviledge_promotion")
    private Integer priviledgePromotion;

    @Schema(description = "是否有会员价格特权")
    @TableField("priviledge_member_price")
    private Integer priviledgeMemberPrice;

    @Schema(description = "是否有生日特权")
    @TableField("priviledge_birthday")
    private Integer priviledgeBirthday;

    @TableField("note")
    private String note;
}

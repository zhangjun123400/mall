package com.zhangjun.mall.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 会员统计信息
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_member_statistics_info")
@Schema(name = "UmsMemberStatisticsInfo", description = "会员统计信息")
public class UmsMemberStatisticsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @Schema(description = "累计消费金额")
    @TableField("consume_amount")
    private BigDecimal consumeAmount;

    @Schema(description = "订单数量")
    @TableField("order_count")
    private Integer orderCount;

    @Schema(description = "优惠券数量")
    @TableField("coupon_count")
    private Integer couponCount;

    @Schema(description = "评价数")
    @TableField("comment_count")
    private Integer commentCount;

    @Schema(description = "退货数量")
    @TableField("return_order_count")
    private Integer returnOrderCount;

    @Schema(description = "登录次数")
    @TableField("login_count")
    private Integer loginCount;

    @Schema(description = "关注数量")
    @TableField("attend_count")
    private Integer attendCount;

    @Schema(description = "粉丝数量")
    @TableField("fans_count")
    private Integer fansCount;

    @TableField("collect_product_count")
    private Integer collectProductCount;

    @TableField("collect_subject_count")
    private Integer collectSubjectCount;

    @TableField("collect_topic_count")
    private Integer collectTopicCount;

    @TableField("collect_comment_count")
    private Integer collectCommentCount;

    @TableField("invite_friend_count")
    private Integer inviteFriendCount;

    @Schema(description = "最后一次下订单时间")
    @TableField("recent_order_time")
    private LocalDateTime recentOrderTime;
}

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
 * 订单退货申请
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("oms_order_return_apply")
@Schema(name = "OmsOrderReturnApply", description = "订单退货申请")
public class OmsOrderReturnApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "订单id")
    @TableField("order_id")
    private Long orderId;

    @Schema(description = "收货地址表id")
    @TableField("company_address_id")
    private Long companyAddressId;

    @Schema(description = "退货商品id")
    @TableField("product_id")
    private Long productId;

    @Schema(description = "订单编号")
    @TableField("order_sn")
    private String orderSn;

    @Schema(description = "申请时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "会员用户名")
    @TableField("member_username")
    private String memberUsername;

    @Schema(description = "退款金额")
    @TableField("return_amount")
    private BigDecimal returnAmount;

    @Schema(description = "退货人姓名")
    @TableField("return_name")
    private String returnName;

    @Schema(description = "退货人电话")
    @TableField("return_phone")
    private String returnPhone;

    @Schema(description = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    @TableField("status")
    private Integer status;

    @Schema(description = "处理时间")
    @TableField("handle_time")
    private LocalDateTime handleTime;

    @Schema(description = "商品图片")
    @TableField("product_pic")
    private String productPic;

    @Schema(description = "商品名称")
    @TableField("product_name")
    private String productName;

    @Schema(description = "商品品牌")
    @TableField("product_brand")
    private String productBrand;

    @Schema(description = "商品销售属性：颜色：红色；尺码：xl;")
    @TableField("product_attr")
    private String productAttr;

    @Schema(description = "退货数量")
    @TableField("product_count")
    private Integer productCount;

    @Schema(description = "商品单价")
    @TableField("product_price")
    private BigDecimal productPrice;

    @Schema(description = "商品实际支付单价")
    @TableField("product_real_price")
    private BigDecimal productRealPrice;

    @Schema(description = "原因")
    @TableField("reason")
    private String reason;

    @Schema(description = "描述")
    @TableField("description")
    private String description;

    @Schema(description = "凭证图片，以逗号隔开")
    @TableField("proof_pics")
    private String proofPics;

    @Schema(description = "处理备注")
    @TableField("handle_note")
    private String handleNote;

    @Schema(description = "处理人员")
    @TableField("handle_man")
    private String handleMan;

    @Schema(description = "收货人")
    @TableField("receive_man")
    private String receiveMan;

    @Schema(description = "收货时间")
    @TableField("receive_time")
    private LocalDateTime receiveTime;

    @Schema(description = "收货备注")
    @TableField("receive_note")
    private String receiveNote;
}

package com.zhangjun.model;

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
 * 会员收货地址表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_member_receive_address")
@Schema(name = "UmsMemberReceiveAddress", description = "会员收货地址表")
public class UmsMemberReceiveAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @Schema(description = "收货人名称")
    @TableField("name")
    private String name;

    @TableField("phone_number")
    private String phoneNumber;

    @Schema(description = "是否为默认")
    @TableField("default_status")
    private Integer defaultStatus;

    @Schema(description = "邮政编码")
    @TableField("post_code")
    private String postCode;

    @Schema(description = "省份/直辖市")
    @TableField("province")
    private String province;

    @Schema(description = "城市")
    @TableField("city")
    private String city;

    @Schema(description = "区")
    @TableField("region")
    private String region;

    @Schema(description = "详细地址(街道)")
    @TableField("detail_address")
    private String detailAddress;
}

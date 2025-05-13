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
 * 公司收发货地址表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("oms_company_address")
@Schema(name = "OmsCompanyAddress", description = "公司收发货地址表")
public class OmsCompanyAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "地址名称")
    @TableField("address_name")
    private String addressName;

    @Schema(description = "默认发货地址：0->否；1->是")
    @TableField("send_status")
    private Integer sendStatus;

    @Schema(description = "是否默认收货地址：0->否；1->是")
    @TableField("receive_status")
    private Integer receiveStatus;

    @Schema(description = "收发货人姓名")
    @TableField("name")
    private String name;

    @Schema(description = "收货人电话")
    @TableField("phone")
    private String phone;

    @Schema(description = "省/直辖市")
    @TableField("province")
    private String province;

    @Schema(description = "市")
    @TableField("city")
    private String city;

    @Schema(description = "区")
    @TableField("region")
    private String region;

    @Schema(description = "详细地址")
    @TableField("detail_address")
    private String detailAddress;
}

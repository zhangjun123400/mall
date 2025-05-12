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
 * 首页推荐品牌表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("sms_home_brand")
@Schema(name = "SmsHomeBrand", description = "首页推荐品牌表")
public class SmsHomeBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("brand_id")
    private Long brandId;

    @TableField("brand_name")
    private String brandName;

    @TableField("recommend_status")
    private Integer recommendStatus;

    @TableField("sort")
    private Integer sort;
}

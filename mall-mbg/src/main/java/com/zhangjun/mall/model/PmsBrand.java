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
 * 品牌表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("pms_brand")
@Schema(name = "PmsBrand", description = "品牌表")
public class PmsBrand implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @Schema(description = "首字母")
    @TableField("first_letter")
    private String firstLetter;

    @TableField("sort")
    private Integer sort;

    @Schema(description = "是否为品牌制造商：0->不是；1->是")
    @TableField("factory_status")
    private Integer factoryStatus;

    @TableField("show_status")
    private Integer showStatus;

    @Schema(description = "产品数量")
    @TableField("product_count")
    private Integer productCount;

    @Schema(description = "产品评论数量")
    @TableField("product_comment_count")
    private Integer productCommentCount;

    @Schema(description = "品牌logo")
    @TableField("logo")
    private String logo;

    @Schema(description = "专区大图")
    @TableField("big_pic")
    private String bigPic;

    @Schema(description = "品牌故事")
    @TableField("brand_story")
    private String brandStory;
}

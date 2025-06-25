package com.zhangjun.mall.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 品牌表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Data
public class PmsBrandParam{

    @TableField("name")
    private String name;

    @Schema(description = "首字母")
    private String firstLetter;

    @Schema(description = "sort")
    private Integer sort;

    @Schema(description = "是否为品牌制造商：0->不是；1->是")
    private Integer factoryStatus;

    @Schema(description = "显示状态")
    private Integer showStatus;

    @Schema(description = "产品数量")
    private Integer productCount;

    @Schema(description = "产品评论数量")
    private Integer productCommentCount;

    @Schema(description = "品牌logo")
    private String logo;

    @Schema(description = "专区大图")
    private String bigPic;

    @Schema(description = "品牌故事")
    private String brandStory;
}

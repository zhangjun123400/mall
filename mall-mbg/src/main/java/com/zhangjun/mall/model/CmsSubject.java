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
 * 专题表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("cms_subject")
@Schema(name = "CmsSubject", description = "专题表")
public class CmsSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("category_id")
    private Long categoryId;

    @TableField("title")
    private String title;

    @Schema(description = "专题主图")
    @TableField("pic")
    private String pic;

    @Schema(description = "关联产品数量")
    @TableField("product_count")
    private Integer productCount;

    @TableField("recommend_status")
    private Integer recommendStatus;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("collect_count")
    private Integer collectCount;

    @TableField("read_count")
    private Integer readCount;

    @TableField("comment_count")
    private Integer commentCount;

    @Schema(description = "画册图片用逗号分割")
    @TableField("album_pics")
    private String albumPics;

    @TableField("description")
    private String description;

    @Schema(description = "显示状态：0->不显示；1->显示")
    @TableField("show_status")
    private Integer showStatus;

    @TableField("content")
    private String content;

    @Schema(description = "转发数")
    @TableField("forward_count")
    private Integer forwardCount;

    @Schema(description = "专题分类名称")
    @TableField("category_name")
    private String categoryName;
}

package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户举报表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("cms_member_report")
@Schema(name = "CmsMemberReport", description = "用户举报表")
public class CmsMemberReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @Schema(description = "举报类型：0->商品评价；1->话题内容；2->用户评论")
    @TableField("report_type")
    private Integer reportType;

    @Schema(description = "举报人")
    @TableField("report_member_name")
    private String reportMemberName;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("report_object")
    private String reportObject;

    @Schema(description = "举报状态：0->未处理；1->已处理")
    @TableField("report_status")
    private Integer reportStatus;

    @Schema(description = "处理结果：0->无效；1->有效；2->恶意")
    @TableField("handle_status")
    private Integer handleStatus;

    @TableField("note")
    private String note;
}

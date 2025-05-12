package com.zhangjun.model;

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
 * 后台用户权限表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_permission")
@Schema(name = "UmsPermission", description = "后台用户权限表")
public class UmsPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "父级权限id")
    @TableField("pid")
    private Long pid;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "权限值")
    @TableField("value")
    private String value;

    @Schema(description = "图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "权限类型：0->目录；1->菜单；2->按钮（接口绑定权限）")
    @TableField("type")
    private Integer type;

    @Schema(description = "前端资源路径")
    @TableField("uri")
    private String uri;

    @Schema(description = "启用状态；0->禁用；1->启用")
    @TableField("status")
    private Integer status;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;
}

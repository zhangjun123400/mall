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
 * 后台菜单表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_menu")
@Schema(name = "UmsMenu", description = "后台菜单表")
public class UmsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "父级ID")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "菜单名称")
    @TableField("title")
    private String title;

    @Schema(description = "菜单级数")
    @TableField("level")
    private Integer level;

    @Schema(description = "菜单排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "前端名称")
    @TableField("name")
    private String name;

    @Schema(description = "前端图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "前端隐藏")
    @TableField("hidden")
    private Integer hidden;
}

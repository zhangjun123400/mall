package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_member")
@Schema(name = "UmsMember", description = "会员表")
public class UmsMember implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_level_id")
    private Long memberLevelId;

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "手机号码")
    @TableField("phone")
    private String phone;

    @Schema(description = "帐号启用状态:0->禁用；1->启用")
    @TableField("status")
    private Integer status;

    @Schema(description = "注册时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "头像")
    @TableField("icon")
    private String icon;

    @Schema(description = "性别：0->未知；1->男；2->女")
    @TableField("gender")
    private Integer gender;

    @Schema(description = "生日")
    @TableField("birthday")
    private LocalDate birthday;

    @Schema(description = "所做城市")
    @TableField("city")
    private String city;

    @Schema(description = "职业")
    @TableField("job")
    private String job;

    @Schema(description = "个性签名")
    @TableField("personalized_signature")
    private String personalizedSignature;

    @Schema(description = "用户来源")
    @TableField("source_type")
    private Integer sourceType;

    @Schema(description = "积分")
    @TableField("integration")
    private Integer integration;

    @Schema(description = "成长值")
    @TableField("growth")
    private Integer growth;

    @Schema(description = "剩余抽奖次数")
    @TableField("luckey_count")
    private Integer luckeyCount;

    @Schema(description = "历史积分数量")
    @TableField("history_integration")
    private Integer historyIntegration;
}

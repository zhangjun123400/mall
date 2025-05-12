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
 * 会员登录记录
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("ums_member_login_log")
@Schema(name = "UmsMemberLoginLog", description = "会员登录记录")
public class UmsMemberLoginLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("member_id")
    private Long memberId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("ip")
    private String ip;

    @TableField("city")
    private String city;

    @Schema(description = "登录类型：0->PC；1->android;2->ios;3->小程序")
    @TableField("login_type")
    private Integer loginType;

    @TableField("province")
    private String province;
}

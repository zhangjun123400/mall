package com.zhangjun.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author zhangjun
 * @since 2025-05-12
 */
@Getter
@Setter
@TableName("pms_product")
@Schema(name = "PmsProduct", description = "商品信息")
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("brand_id")
    private Long brandId;

    @TableField("product_category_id")
    private Long productCategoryId;

    @TableField("feight_template_id")
    private Long feightTemplateId;

    @TableField("product_attribute_category_id")
    private Long productAttributeCategoryId;

    @TableField("name")
    private String name;

    @TableField("pic")
    private String pic;

    @Schema(description = "货号")
    @TableField("product_sn")
    private String productSn;

    @Schema(description = "删除状态：0->未删除；1->已删除")
    @TableField("delete_status")
    private Integer deleteStatus;

    @Schema(description = "上架状态：0->下架；1->上架")
    @TableField("publish_status")
    private Integer publishStatus;

    @Schema(description = "新品状态:0->不是新品；1->新品")
    @TableField("new_status")
    private Integer newStatus;

    @Schema(description = "推荐状态；0->不推荐；1->推荐")
    @TableField("recommand_status")
    private Integer recommandStatus;

    @Schema(description = "审核状态：0->未审核；1->审核通过")
    @TableField("verify_status")
    private Integer verifyStatus;

    @Schema(description = "排序")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "销量")
    @TableField("sale")
    private Integer sale;

    @TableField("price")
    private BigDecimal price;

    @Schema(description = "促销价格")
    @TableField("promotion_price")
    private BigDecimal promotionPrice;

    @Schema(description = "赠送的成长值")
    @TableField("gift_growth")
    private Integer giftGrowth;

    @Schema(description = "赠送的积分")
    @TableField("gift_point")
    private Integer giftPoint;

    @Schema(description = "限制使用的积分数")
    @TableField("use_point_limit")
    private Integer usePointLimit;

    @Schema(description = "副标题")
    @TableField("sub_title")
    private String subTitle;

    @Schema(description = "商品描述")
    @TableField("description")
    private String description;

    @Schema(description = "市场价")
    @TableField("original_price")
    private BigDecimal originalPrice;

    @Schema(description = "库存")
    @TableField("stock")
    private Integer stock;

    @Schema(description = "库存预警值")
    @TableField("low_stock")
    private Integer lowStock;

    @Schema(description = "单位")
    @TableField("unit")
    private String unit;

    @Schema(description = "商品重量，默认为克")
    @TableField("weight")
    private BigDecimal weight;

    @Schema(description = "是否为预告商品：0->不是；1->是")
    @TableField("preview_status")
    private Integer previewStatus;

    @Schema(description = "以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    @TableField("service_ids")
    private String serviceIds;

    @TableField("keywords")
    private String keywords;

    @TableField("note")
    private String note;

    @Schema(description = "画册图片，连产品图片限制为5张，以逗号分割")
    @TableField("album_pics")
    private String albumPics;

    @TableField("detail_title")
    private String detailTitle;

    @TableField("detail_desc")
    private String detailDesc;

    @Schema(description = "产品详情网页内容")
    @TableField("detail_html")
    private String detailHtml;

    @Schema(description = "移动端网页详情")
    @TableField("detail_mobile_html")
    private String detailMobileHtml;

    @Schema(description = "促销开始时间")
    @TableField("promotion_start_time")
    private LocalDateTime promotionStartTime;

    @Schema(description = "促销结束时间")
    @TableField("promotion_end_time")
    private LocalDateTime promotionEndTime;

    @Schema(description = "活动限购数量")
    @TableField("promotion_per_limit")
    private Integer promotionPerLimit;

    @Schema(description = "促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    @TableField("promotion_type")
    private Integer promotionType;

    @Schema(description = "品牌名称")
    @TableField("brand_name")
    private String brandName;

    @Schema(description = "商品分类名称")
    @TableField("product_category_name")
    private String productCategoryName;
}

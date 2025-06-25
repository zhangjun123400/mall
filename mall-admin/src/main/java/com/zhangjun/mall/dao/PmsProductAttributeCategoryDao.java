package com.zhangjun.mall.dao;

import com.zhangjun.mall.dto.PmsProductAttributeCategoryItem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/18 22:54
 * @Version 1.0
 */
public interface PmsProductAttributeCategoryDao {

    @Select("SELECT\n" +
            "            pac.id,\n" +
            "            pac.name,\n" +
            "            pa.id attr_id,\n" +
            "            pa.name attr_name\n" +
            "        FROM\n" +
            "            pms_product_attribute_category pac\n" +
            "            LEFT JOIN pms_product_attribute pa ON pac.id = pa.product_attribute_category_id\n" +
            "        AND pa.type=1;")
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}

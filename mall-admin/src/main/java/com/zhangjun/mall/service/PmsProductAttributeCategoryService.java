package com.zhangjun.mall.service;

import com.zhangjun.mall.dto.PmsProductAttributeCategoryItem;
import com.zhangjun.mall.dto.PmsProductAttributeCategoryParam;
import com.zhangjun.mall.model.PmsProductAttributeCategory;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/18 22:37
 * @Version 1.0
 */
public interface PmsProductAttributeCategoryService {

    /**
     *
     * @param name
     * @return
     */
    public int create(String name);

    /**
     *
     * @param id
     * @param name
     * @return
     */
    public int update(Long id, String name);

    /**
     *
     * @param id
     * @return
     */
    public int delete(Long id);

    /**
     * 获取属性分类详情
     * @param id
     * @return
     */
    PmsProductAttributeCategory getItem(Long id);

    /**
     *分页查询属性分类
     * @return
     */
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum);

    /**
     *获取包含属性的属性分类
     * @return
     */
    List<PmsProductAttributeCategoryItem> getListWithAttr();
}

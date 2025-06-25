package com.zhangjun.mall.service;

import com.zhangjun.mall.model.UmsResourceCategory;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 21:12
 * @Version 1.0
 */
public interface UmsResourceCategoryService {

    /**
     * 获取所有资源分类
     * @return
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     * @param umsResourceCategory
     * @return
     */
    int create(UmsResourceCategory umsResourceCategory);

    /**
     * 修改资源分类
     * @param id
     * @param umsResourceCategory
     * @return
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 删除资源分类
     * @param id
     * @return
     */
    int delete(Long id);
}

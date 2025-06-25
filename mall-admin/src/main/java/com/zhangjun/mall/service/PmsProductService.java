package com.zhangjun.mall.service;

import com.zhangjun.mall.model.PmsProduct;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/28 22:32
 * @Version 1.0
 */
public interface PmsProductService {


    public List<PmsProduct> listAll();

    public List<PmsProduct> list(Long brandId, String productCategoryId, String productSn, Integer pageSize, Integer pageNum);

    //public void create()
}

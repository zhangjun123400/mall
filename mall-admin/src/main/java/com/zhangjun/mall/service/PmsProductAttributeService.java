package com.zhangjun.mall.service;

import com.zhangjun.mall.dto.PmsProductAttributeParam;
import com.zhangjun.mall.model.PmsProductAttribute;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/18 23:09
 * @Version 1.0
 */
public interface PmsProductAttributeService {

    /**
     * 添加商品属性
     * @param pmsProductAttributeParam
     * @return
     */
    @Transactional
    public int create(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 更新商品属性
     * @param id
     * @param pmsProductAttributeParam
     * @return
     */
    int update(Long id, PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 删除商品属性
     * @param ids
     * @return
     */
    @Transactional
    int delete(List<Long> ids);

    /**
     * 根据ID获取商品属性详情
     * @param id
     * @return
     */
    PmsProductAttribute getItem(Long id);

    /**
     * 根据分类ID和类型分页获取商品属性
     * @param cid   cid 分类id
     * @param type type 0->规格；1->参数
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<PmsProductAttribute> getList(Long cid,Integer type,Integer pageNum, Integer pageSize);


}

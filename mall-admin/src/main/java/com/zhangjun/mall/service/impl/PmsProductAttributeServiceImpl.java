package com.zhangjun.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.zhangjun.mall.dto.PmsProductAttributeParam;
import com.zhangjun.mall.mapper.PmsProductAttributeMapper;
import com.zhangjun.mall.model.PmsProductAttribute;
import com.zhangjun.mall.service.PmsProductAttributeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/18 23:16
 * @Version 1.0
 */
@Service
public class PmsProductAttributeServiceImpl implements PmsProductAttributeService {

    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;

    @Override
    public int create(PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute productAttribute = new PmsProductAttribute();
        BeanUtils.copyProperties(pmsProductAttributeParam, productAttribute);
        return productAttributeMapper.insert(productAttribute);
    }

    @Override
    public int update(Long id, PmsProductAttributeParam pmsProductAttributeParam) {
        PmsProductAttribute productAttribute = productAttributeMapper.selectById(id);
        BeanUtils.copyProperties(pmsProductAttributeParam, productAttribute);

        return productAttributeMapper.updateById(productAttribute);
    }

    @Override
    public int delete(List<Long> ids) {
        return productAttributeMapper.deleteBatchIds(ids);
    }

    @Override
    public PmsProductAttribute getItem(Long id) {
        return productAttributeMapper.selectById(id);
    }

    @Override
    public List<PmsProductAttribute> getList(Long cid, Integer type, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<PmsProductAttribute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsProductAttribute::getProductAttributeCategoryId ,cid)
                .eq(PmsProductAttribute::getType, type);
        return productAttributeMapper.selectList(queryWrapper);
    }
}

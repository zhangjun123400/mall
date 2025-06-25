package com.zhangjun.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.zhangjun.mall.dto.PmsBrandParam;
import com.zhangjun.mall.mapper.PmsBrandMapper;
import com.zhangjun.mall.mapper.PmsProductMapper;
import com.zhangjun.mall.model.PmsBrand;
import com.zhangjun.mall.model.PmsProduct;
import com.zhangjun.mall.service.PmsBrandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/17 23:07
 * @Version 1.0
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Autowired
    PmsBrandMapper pmsBrandMapper;

    @Autowired
    private PmsProductMapper productMapper;

    @Override
    public List<PmsBrand> listAll() {
        return pmsBrandMapper.selectList(null);
    }

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1).toUpperCase());
        }
        return pmsBrandMapper.insert(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }

        //更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<PmsProduct>();
        queryWrapper.eq(PmsProduct::getBrandId,id);
        productMapper.update(product,queryWrapper);

        return pmsBrandMapper.updateById(pmsBrand);
    }

    @Override
    public int deleteBrand(Long id) {
        return pmsBrandMapper.deleteById(id);
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsBrand::getId, ids);
        return pmsBrandMapper.delete(queryWrapper);
    }

    @Override
    public List<PmsBrand> listBrand(String keyword, Integer showStatus, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<PmsBrand>();
        queryWrapper.like(StrUtil.isNotEmpty(keyword), PmsBrand::getName, keyword);
        if (showStatus != null) {
            queryWrapper.eq(PmsBrand::getShowStatus, showStatus);
        }
        queryWrapper.orderByDesc(PmsBrand::getId);

        return pmsBrandMapper.selectList(queryWrapper);
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return pmsBrandMapper.selectById(id);
    }

    @Override
    public int updateShowStatus(List<Long> id, Integer showStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setShowStatus(showStatus);
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsBrand::getId, id);
        return pmsBrandMapper.update(pmsBrand, queryWrapper);
    }

    @Override
    public int updateFactoryStatus(List<Long> id, Integer factoryStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);
        LambdaQueryWrapper<PmsBrand> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(PmsBrand::getId, id);
        return pmsBrandMapper.update(pmsBrand, queryWrapper);
    }
}

package com.zhangjun.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangjun.mall.mapper.UmsResourceCategoryMapper;
import com.zhangjun.mall.model.UmsResourceCategory;
import com.zhangjun.mall.service.UmsResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 21:15
 * @Version 1.0
 */
@Service
public class UmsResourceCategoryServiceImpl implements UmsResourceCategoryService {

    @Autowired
    private UmsResourceCategoryMapper umsResourceCategoryMapper;


    @Override
    public List<UmsResourceCategory> listAll() {

        QueryWrapper<UmsResourceCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("sort");
        return umsResourceCategoryMapper.selectList(null);
    }

    @Override
    public int create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(LocalDateTime.now());
        return umsResourceCategoryMapper.insert(umsResourceCategory);
    }

    @Override
    public int update(Long id, UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setId(id);
        return umsResourceCategoryMapper.updateById(umsResourceCategory);
    }

    @Override
    public int delete(Long id) {
        return umsResourceCategoryMapper.deleteById(id);
    }
}

package com.zhangjun.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.zhangjun.mall.dao.PmsProductAttributeCategoryDao;
import com.zhangjun.mall.dto.PmsProductAttributeCategoryItem;
import com.zhangjun.mall.dto.PmsProductAttributeCategoryParam;
import com.zhangjun.mall.mapper.PmsProductAttributeCategoryMapper;
import com.zhangjun.mall.model.PmsProductAttributeCategory;
import com.zhangjun.mall.service.PmsProductAttributeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/18 22:53
 * @Version 1.0
 */
@Service
public class PmsProductAttributeCategoryServiceImpl implements PmsProductAttributeCategoryService {

    @Autowired
    PmsProductAttributeCategoryMapper pmsProductAttributeCategoryMapper;

    @Autowired
    PmsProductAttributeCategoryDao pmsProductAttributeCategoryDao;

    @Override
    public int create(String name) {
        PmsProductAttributeCategory pmsProductAttributeCategory = new PmsProductAttributeCategory();
        pmsProductAttributeCategory.setName(name);

        return pmsProductAttributeCategoryMapper.insert(pmsProductAttributeCategory);
    }

    @Override
    public int update(Long id, String name) {
        PmsProductAttributeCategory pmsProductAttributeCategory = pmsProductAttributeCategoryMapper.selectById(id);
        pmsProductAttributeCategory.setName(name);
        pmsProductAttributeCategory.setId(id);
        return pmsProductAttributeCategoryMapper.updateById(pmsProductAttributeCategory);
    }

    @Override
    public int delete(Long id) {
        return pmsProductAttributeCategoryMapper.deleteById(id);
    }

    @Override
    public PmsProductAttributeCategory getItem(Long id) {
        return pmsProductAttributeCategoryMapper.selectById(id);
    }

    @Override
    public List<PmsProductAttributeCategory> getList(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);

        return pmsProductAttributeCategoryMapper.selectList(null);
    }

    @Override
    public List<PmsProductAttributeCategoryItem> getListWithAttr() {
        return pmsProductAttributeCategoryDao.getListWithAttr();
    }
}

package com.zhangjun.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.zhangjun.mall.mapper.UmsResourceMapper;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 后台资源管理Service实现类
 * @Author zhangjun
 * @Date 2025/5/22 21:49
 * @Version 1.0
 */
@Service
public class UmsResourceServiceImpl implements UmsResourceService {

    @Autowired
    private UmsResourceMapper umsResourceMapper;

    @Override
    public int create(UmsResource umsResource) {
        umsResource.setCreateTime(LocalDateTime.now());
        return umsResourceMapper.insert(umsResource);
    }

    @Override
    public int update(Long id, UmsResource umsResource) {
        umsResource.setId(id);
        return umsResourceMapper.updateById(umsResource);
    }

    @Override
    public UmsResource getItem(Long id) {
        return umsResourceMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        return umsResourceMapper.deleteById(id);
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<UmsResource> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsResource::getCategoryId,categoryId)
                .like(UmsResource::getName,nameKeyword)
                .or()
                .like(UmsResource::getUrl,urlKeyword);

        return umsResourceMapper.selectList(queryWrapper);
    }

    @Override
    public List<UmsResource> listAll() {
        QueryWrapper<UmsResource> queryWrapper = new QueryWrapper<>();
        return umsResourceMapper.selectList(queryWrapper);
    }
}

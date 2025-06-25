package com.zhangjun.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.zhangjun.mall.mapper.UmsResourceMapper;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.service.UmsAdminCacheService;
import com.zhangjun.mall.service.UmsResourceService;
import com.zhangjun.mall.utils.SpringUtil;
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
        getCacheService().delResourceByResource(id);
        return umsResourceMapper.updateById(umsResource);
    }

    @Override
    public UmsResource getItem(Long id) {
        return umsResourceMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        getCacheService().delResourceByResource(id);
        return umsResourceMapper.deleteById(id);
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);

        LambdaQueryWrapper<UmsResource> queryWrapper = new LambdaQueryWrapper<>();

        if (categoryId !=null){
            queryWrapper.eq(UmsResource::getCategoryId,categoryId);
        }

        if (StrUtil.isNotEmpty(nameKeyword) || StrUtil.isNotEmpty(urlKeyword)){
            queryWrapper.and(wrapper -> {
                if (StrUtil.isNotEmpty(nameKeyword)){
                    wrapper.like(UmsResource::getName,nameKeyword);
                }
                if (StrUtil.isNotEmpty(urlKeyword)){
                    wrapper.or().like(UmsResource::getUrl,urlKeyword);
                }
            });
        }

        return umsResourceMapper.selectList(queryWrapper);
    }

    @Override
    public List<UmsResource> listAll() {
        QueryWrapper<UmsResource> queryWrapper = new QueryWrapper<>();
        return umsResourceMapper.selectList(queryWrapper);
    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }
}

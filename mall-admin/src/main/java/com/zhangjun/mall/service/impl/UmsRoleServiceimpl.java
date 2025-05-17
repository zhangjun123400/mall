package com.zhangjun.mall.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.zhangjun.mall.dao.UmsRoleDao;
import com.zhangjun.mall.mapper.UmsRoleMapper;
import com.zhangjun.mall.mapper.UmsRoleMenuRelationMapper;
import com.zhangjun.mall.mapper.UmsRoleResourceRelationMapper;
import com.zhangjun.mall.model.*;
import com.zhangjun.mall.service.UmsAdminCacheService;
import com.zhangjun.mall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/16 00:22
 * @Version 1.0
 */
@Service
public class UmsRoleServiceimpl implements UmsRoleService {

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Autowired
    private UmsAdminCacheService umsAdminCacheService;

    @Autowired
    private UmsRoleDao umsRoleDao;

    @Autowired
    private UmsRoleMenuRelationMapper umsRoleMenuRelationMapper;

    @Autowired
    private UmsRoleResourceRelationMapper umsRoleResourceRelationMapper;

    @Override
    public int create(UmsRole umsRole) {

        umsRole.setCreateTime(LocalDateTime.now());
        umsRole.setAdminCount(0);
        umsRole.setSort(0);
        return umsRoleMapper.insert(umsRole);
    }

    @Override
    public int update(Long id, UmsRole umsRole) {
        umsRole.setId(id);
        return umsRoleMapper.updateById(umsRole);
    }

    @Override
    public int delete(List<Long> ids) {
        int count= umsRoleMapper.deleteBatchIds(ids);
        umsAdminCacheService.delResourceListByRoleIds(ids);
        return count;
    }

    @Override
    public List<UmsRole> list() {
        return umsRoleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<UmsRole> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)){
            queryWrapper.like("name",keyword);
        }
        return umsRoleMapper.selectList(queryWrapper);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {

        return umsRoleDao.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return umsRoleDao.getMenuListByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return umsRoleDao.getResourceListByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        //先删除原有关系
        QueryWrapper<UmsRoleMenuRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        umsRoleMenuRelationMapper.delete(queryWrapper);

        //新增新的关系
        for (Long menuId:menuIds){
            UmsRoleMenuRelation umsRoleMenuRelation = new  UmsRoleMenuRelation();
            umsRoleMenuRelation.setRoleId(roleId);
            umsRoleMenuRelation.setMenuId(menuId);
            umsRoleMenuRelationMapper.insert(umsRoleMenuRelation);
        }

        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        //先删除原有资源
        QueryWrapper<UmsRoleResourceRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id",roleId);
        umsRoleResourceRelationMapper.delete(queryWrapper);

        //新增新有资源
        for (Long resourceId:resourceIds){
            UmsRoleResourceRelation umsRoleResourceRelation = new UmsRoleResourceRelation();
            umsRoleResourceRelation.setRoleId(roleId);
            umsRoleResourceRelation.setResourceId(resourceId);
            umsRoleResourceRelationMapper.insert(umsRoleResourceRelation);
        }

        umsAdminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}

package com.zhangjun.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.zhangjun.mall.dto.UmsMenuNode;
import com.zhangjun.mall.mapper.UmsMenuMapper;
import com.zhangjun.mall.model.UmsMenu;
import com.zhangjun.mall.service.UmsMenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangjun
 * @Date 2025/5/23 23:09
 * @Version 1.0
 */
@Service
public class UmsMenuServiceImpl implements UmsMenuService {

    @Autowired
    private UmsMenuMapper umsMenuMapper;

    /**
     * 修改菜单层级
     * @param umsMenu
     */
    private void updateLevel(UmsMenu umsMenu){
        if (umsMenu.getParentId()==0){
            //没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        }else {
            //有父菜单时选择根据父菜单level设置
            UmsMenu parentMenu = umsMenuMapper.selectById(umsMenu.getParentId());
            if (parentMenu !=null){
                umsMenu.setLevel(parentMenu.getLevel() + 1);
            }else {
                umsMenu.setLevel(0);
            }
        }
    }


    @Override
    public int create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(LocalDateTime.now());
        updateLevel(umsMenu);
        return umsMenuMapper.insert(umsMenu);
    }

    @Override
    public int update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return umsMenuMapper.updateById(umsMenu);
    }

    @Override
    public UmsMenu getItem(Long id) {
        return umsMenuMapper.selectById(id);
    }

    @Override
    public int delete(Long id) {
        return umsMenuMapper.deleteById(id);
    }

    @Override
    public List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        LambdaQueryWrapper<UmsMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsMenu::getParentId,parentId).orderByDesc(UmsMenu::getSort);
        return umsMenuMapper.selectList(queryWrapper);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> umsMenuList = umsMenuMapper.selectList(new QueryWrapper<>());
        List<UmsMenuNode> result = umsMenuList.stream()
                .filter(menu->menu.getParentId().equals(0L))
                .map(menu->covertMenuNode(menu,umsMenuList))
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public int updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return umsMenuMapper.updateById(umsMenu);
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     * @param umsMenu
     * @param umsMenuList
     * @return
     */
    private UmsMenuNode covertMenuNode(UmsMenu umsMenu,List<UmsMenu> umsMenuList){
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(umsMenu,node);
        List<UmsMenuNode> children = umsMenuList.stream()
                .filter(subMenu->subMenu.getParentId().equals(umsMenu.getId()))
                .map(subMenu->covertMenuNode(subMenu,umsMenuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}

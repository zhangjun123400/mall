package com.zhangjun.mall.service;

import com.zhangjun.mall.dto.UmsMenuNode;
import com.zhangjun.mall.model.UmsMenu;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 22:51
 * @Version 1.0
 */
public interface UmsMenuService {

    /**
     *创建后台菜单
     * @param umsMenu
     * @return
     */
    int create(UmsMenu umsMenu);


    /**
     * 修改后台菜单
     * @param id
     * @param umsMenu
     * @return
     */
    int update(Long id, UmsMenu umsMenu);

    /**
     * 根据ID获取菜单详情
     * @param id
     * @return
     */
    UmsMenu getItem(Long id);

    /**
     * 根据ID删除菜单
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询后台菜单
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsMenu> list(Long parentId,Integer pageSize,Integer pageNum);

    /**
     * 树形结构返回所有的菜单列表
     * @return
     */
    List<UmsMenuNode> treeList();

    /**
     * 修改菜单显示状态
     * @param id
     * @param hidden
     * @return
     */
    int updateHidden(Long id,Integer hidden);
}

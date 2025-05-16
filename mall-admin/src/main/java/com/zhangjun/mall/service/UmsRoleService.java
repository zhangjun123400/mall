package com.zhangjun.mall.service;

import com.zhangjun.mall.model.UmsMenu;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理Service
 * @Author zhangjun
 * @Date 2025/5/15 22:10
 * @Version 1.0
 */
public interface UmsRoleService {

    /**
     * 添加角色
     * @param umsRole
     * @return
     */
    int create(UmsRole umsRole);

    /**
     * 修改角色
     * @param id
     * @param umsRole
     * @return
     */
    int update(Long id, UmsRole umsRole);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    int delete(List<Long> ids);


    /**
     * 获取所有角色列表
     * @return
     */
    List<UmsRole> list();


    /**
     * 分页获取角色列表
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsRole> list(String keyword,Integer pageSize,Integer pageNum);

    /**
     * 据管理员ID获取对应菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     * @param roleId
     * @return
     */
    List<UmsMenu> listMenu(Long roleId);


    /**
     * 获取角色相关资源
     * @param roleId
     * @return
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    @Transactional
    int allocMenu(Long roleId,List<Long> menuIds);

    /**
     * 给角色分配资源
     * @param roleId
     * @param resourceIds
     * @return
     */
    int allocResource(Long roleId,List<Long> resourceIds);




}

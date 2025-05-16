package com.zhangjun.mall.service;

import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsResource;

import java.util.List;

/**
 * 后台用户缓存管理Service
 * @Author zhangjun
 * @Date 2025/5/15 21:18
 * @Version 1.0
 */
public interface UmsAdminCacheService {

    /**
     * 删除后台用户缓存
     * @param adminId
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     * @param adminId
     */
    void delResourceList(Long adminId);

    /**
     *当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleIds
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     *
     * 当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleId
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     * @param resourceId
     */
    void delResourceByResource(Long resourceId);

    /**
     * 获取缓存后台用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台用户信息
     * @param umsAdmin
     */
    void setAdmin(UmsAdmin umsAdmin);

    /**
     *
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置缓存后台用户资源列表
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);



}

package com.zhangjun.mall.dao;

import com.zhangjun.mall.model.UmsMenu;
import com.zhangjun.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/15 21:51
 * @Version 1.0
 */
public interface UmsRoleDao {

    /**
     * 根据后台用户ID获取菜单
     * @param adminId
     * @return
     */
    @Select("SELECT t1.* FROM ums_menu t1 \n" +
            "INNER JOIN ums_role_menu_relation t2 ON t1.id = t2.menu_id\n" +
            "INNER JOIN ums_role t3 ON t3.id=t2.role_id\n" +
            "INNER JOIN ums_admin_role_relation t4 ON t4.role_id = t3.id\n" +
            "INNER JOIN ums_admin t5 ON t5.id = t4.admin_id\n" +
            "WHERE t5.id=#{adminId}")
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);


    /**
     * 根据角色ID获取菜单
     * @param roleId
     * @return
     */
    @Select("SELECT t1.* FROM ums_menu t1 \n" +
            "INNER JOIN ums_role_menu_relation t2 ON t1.id= t2.menu_id\n" +
            "INNER JOIN ums_role t3 ON t2.role_id = t3.id\n" +
            "where t3.id = #{roleId}")
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);


    /**
     * 根据角色ID获取资源
     * @param roleId
     * @return
     */
    @Select("SELECT t1.* from ums_resource t1 \n" +
            "INNER JOIN ums_role_resource_relation t2 ON t1.id=t2.role_id\n" +
            "INNER JOIN ums_role t3 ON t2.role_id=t3.id\n" +
            "WHERE t3.id=#{roleId}")
    List<UmsResource> getResourceListByRoleId(@Param("roleId") Long roleId);


}

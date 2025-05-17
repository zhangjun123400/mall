package com.zhangjun.mall.dao;

import com.zhangjun.mall.model.UmsAdminRoleRelation;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.model.UmsRole;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/15 23:32
 * @Version 1.0
 */
public interface UmsAdminRoleRelationDao {

    /**
     * 批量插入用户角色关系
     * @param adminRoleRelationsList
     * @return
     */
    @Insert("<script>\n" +
            "INSERT INTO ums_admin_role_relation(admin_id, role_id) VALUES\n" +
            "<foreach collection='list' item='item' separator=','>\n" +
            "(#{item.adminId}, #{item.roleId})\n" +
            "</foreach>\n" +
            "</script>")
    void insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationsList);

    /**
     * 获取用户所有角色
     * @param adminId
     * @return
     */
    @Select("SELECT t1.* FROM ums_role t1 \n" +
            "INNER JOIN ums_admin_role_relation t2 ON t1.id=t2.role_id\n" +
            "INNER JOIN ums_admin t3 ON t3.id = t2.admin_id\n" +
            "where t3.id = #{adminId}")
    List<UmsRole> getRoleListByAdminId(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     * @param adminId
     * @return
     */
    @Select("SELECT t1.* FROM ums_resource t1\n" +
            "INNER JOIN ums_role_resource_relation t2 ON t1.id = t2.resource_id\n" +
            "INNER JOIN ums_role t3 ON t3.id = t2.role_id\n" +
            "INNER JOIN ums_admin_role_relation t4 ON t4.role_id = t3.id\n" +
            "INNER JOIN ums_admin t5 ON t5.id = t4.admin_id\n" +
            "where t5.id =#{adminId}")
    List<UmsResource> getResourceListByAdminId(@Param("adminId") Long adminId);

    @Select("SELECT t1.url FROM ums_resource t1\n" +
            "INNER JOIN ums_role_resource_relation t2 ON t1.id = t2.resource_id\n" +
            "INNER JOIN ums_role t3 ON t3.id = t2.role_id\n" +
            "INNER JOIN ums_admin_role_relation t4 ON t4.role_id = t3.id\n" +
            "INNER JOIN ums_admin t5 ON t5.id = t4.admin_id\n" +
            "where t5.id =#{adminId}")
    List<String> getResourceListByAdminId1(@Param("adminId") Long adminId);
    /**
     * 获取资源相关用户ID列表
     * @param resourceId
     * @return
     */
    @Select("SELECT t1.id FROM ums_admin t1 \n" +
            "INNER JOIN ums_admin_role_relation t2 ON t2.admin_id=t1.id\n" +
            "INNER JOIN ums_role t3 ON t3.id = t2.role_id\n" +
            "INNER JOIN ums_role_resource_relation t4 ON t4.role_id = t3.id\n" +
            "INNER JOIN ums_resource t5 ON t5.id = t4.resource_id\n" +
            "where t5.id=#{resourceId}")
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}

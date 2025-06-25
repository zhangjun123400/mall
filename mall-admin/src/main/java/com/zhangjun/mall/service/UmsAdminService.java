package com.zhangjun.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjun.mall.dto.UmsAdminLoginParam;
import com.zhangjun.mall.dto.UmsAdminParam;
import com.zhangjun.mall.dto.UpdateAdminPasswordParam;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author zhangjun
 * @Date 2025/4/28 17:25
 * @Version 1.0
 */
public interface UmsAdminService extends IService<UmsAdmin> {

        /**
         * 根据用户名获取后台管理员
         *
         * @param userName the username of the admin user to retrieve
         * @return the UmsAdmin object representing the admin user with the specified username,
         *         or null if no such user exists
         */
        public UmsAdmin getAdminByUsername(String userName);

        /**
         * * 登录功能
         * @param umsAdminLoginParam
         * @return
         */
        Map<String,String> login(UmsAdminLoginParam umsAdminLoginParam);

        /**
         * 注册功能
         * @param umsAdminParam
         * @return
         */
        public UmsAdmin register(UmsAdminParam umsAdminParam);

        /**
         * 刷新token的功能
         * @param oldToken
         * @return
         */
        public String refreshToken(String oldToken);


        /**
         * 根据用户id获取用户
         * @param id
         * @return
         */
        UmsAdmin getItem(Long id);

        /**
         * 根据用户名或昵称分页查询用户
         * @param keyword
         * @param pageNum
         * @param pageSize
         * @return
         */
        List<UmsAdmin> list(String keyword,Integer pageNum, Integer pageSize);


        /**
         * 修改指定用户信息
         * @param id
         * @param umsAdmin
         * @return
         */
        int update(Long id,UmsAdmin umsAdmin);

        /**
         * 删除指定用户
         * @param id
         * @return
         */
        int delete(Long id);

        /**
         * 修改用户角色关系
         * @param adminId
         * @param roleIds
         * @return
         */
        @Transactional
        int updateRole(Long adminId,List<Long> roleIds);

        /**
         * 获取用户对应角色
         * @param adminId
         * @return
         */
        List<UmsRole> getRoleList(Long adminId);

        /**
         * 获取指定用户的可访问资源
         * @param adminId
         * @return
         */
        List<UmsResource> getResourceList(Long adminId);

        /**
         * 修改密码
         * @param updateAdminPasswordParam
         * @return
         */
        int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);


        /**
         * 获取用户信息
         * @param username
         * @return
         */
        UserDetails loadUserByUsername(String username);


        /**
         * 获取缓存服务
         * @return
         */
        UmsAdminCacheService getCacheService();

        /**
         * 登出功能
         * @param username
         */
        void logout(String username);

}

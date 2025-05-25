package com.zhangjun.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangjun.mall.dao.UmsAdminRoleRelationDao;
import com.zhangjun.mall.mapper.UmsAdminMapper;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.service.UmsAdminCacheService;
import com.zhangjun.mall.service.UmsAdminService;
import com.zhangjun.mall.utils.SpringUtil;
import com.zhangjun.mall.vo.LoginUser;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/4/28 16:10
 * @Version 1.0
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.isEmpty()) {
            throw new InternalAuthenticationServiceException("用户名为空");
        }

        //1、连接数据库，根据用户名查询账号信息
        UmsAdmin umsAdmin = getCacheService().getAdmin(username);
        if (umsAdmin ==null) {
            LambdaQueryWrapper<UmsAdmin> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UmsAdmin::getUsername, username);

            umsAdmin = umsAdminMapper.selectOne(queryWrapper);
        }

        if (umsAdmin == null) {
            throw new UsernameNotFoundException("无该用户");
        }

        //2、赋权操作 活的数组 从数据库中获取
        List<UmsResource> resourceList = getCacheService().getResourceList(umsAdmin.getId());
        if (CollUtil.isEmpty(resourceList)){
            resourceList = umsAdminRoleRelationDao.getResourceListByAdminId(umsAdmin.getId());
            if (CollUtil.isNotEmpty(resourceList)){
                getCacheService().setResourceList(umsAdmin.getId(),resourceList);
            }
        }
        //3、返回UserDetails对象
        return new LoginUser(umsAdmin,resourceList);
    }

    public UmsAdminCacheService getCacheService() {
        return SpringUtil.getBean(UmsAdminCacheService.class);
    }

}

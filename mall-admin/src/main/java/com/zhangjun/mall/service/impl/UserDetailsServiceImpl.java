package com.zhangjun.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangjun.mall.dao.UmsAdminRoleRelationDao;
import com.zhangjun.mall.mapper.UmsAdminMapper;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.vo.LoginUser;
import jakarta.annotation.Resource;
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

    @Resource
    private UmsAdminMapper userMapper;

    @Resource
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.isEmpty()) {
            throw new InternalAuthenticationServiceException("用户名为空");
        }

        //1、连接数据库，根据用户名查询账号信息
        LambdaQueryWrapper<UmsAdmin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsAdmin::getUsername, username);

        UmsAdmin user =userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("无该用户");
        }

        //2、赋权操作 活的数组 从数据库中获取
        List<UmsResource> umsResourceList = umsAdminRoleRelationDao.getResourceListByAdminId(user.getId());
        //List<String> urlList = umsAdminRoleRelationDao.getResourceListByAdminId1(user.getId());
        //3、返回UserDetails对象
        return new LoginUser(user,umsResourceList);
    }
}

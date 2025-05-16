package com.zhangjun.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangjun.common.service.RedisService;
import com.zhangjun.mall.dao.UmsAdminRoleRelationDao;
import com.zhangjun.mall.mapper.UmsAdminRoleRelationMapper;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsAdminRoleRelation;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.service.UmsAdminCacheService;
import com.zhangjun.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangjun
 * @Date 2025/5/15 22:58
 * @Version 1.0
 */
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UmsAdminService umsAdminService;

    @Autowired
    private UmsAdminRoleRelationMapper umsAdminRoleRelationMapper;

    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;


    @Override
    public void delAdmin(Long adminId) {
        UmsAdmin umsAdmin = umsAdminService.getItem(adminId);
        if (umsAdmin !=null){
            String key = REDIS_DATABASE+":"+REDIS_KEY_ADMIN+":"+umsAdmin.getUsername();
            redisService.del(key);
        }

    }

    @Override
    public void delResourceList(Long adminId) {
        String key = REDIS_DATABASE+":"+REDIS_KEY_RESOURCE_LIST+":"+adminId;
        redisService.del(key);

    }

    @Override
    public void delResourceListByRoleIds(List<Long> roleIds) {
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();

        queryWrapper.in("role_id", roleIds);
        List<UmsAdminRoleRelation> relationList =umsAdminRoleRelationMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getAdminId()).collect(Collectors.toList());
            redisService.del(keys);
        }

    }

    @Override
    public void delResourceListByRole(Long roleId) {
        QueryWrapper<UmsAdminRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", roleId);

        List<UmsAdminRoleRelation> relationList =umsAdminRoleRelationMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(reliation->keyPrefix+reliation.getAdminId()).toList();
            redisService.del(keys);
        }

    }

    @Override
    public void delResourceByResource(Long resourceId) {
        List<Long> adminIdList=adminRoleRelationDao.getAdminIdList(resourceId);
        if (CollUtil.isNotEmpty(adminIdList))
        {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = adminIdList.stream().map(adminId->keyPrefix+adminId).toList();
            redisService.del(keys);
        }
    }

    @Override
    public UmsAdmin getAdmin(String username) {
        String key = REDIS_DATABASE+":"+REDIS_KEY_ADMIN+":"+username;
        return (UmsAdmin) redisService.get(key);
    }

    @Override
    public void setAdmin(UmsAdmin umsAdmin) {
        String key = REDIS_DATABASE+":"+REDIS_KEY_ADMIN+":"+umsAdmin.getUsername();
        redisService.set(key,umsAdmin,REDIS_EXPIRE);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        String key = REDIS_DATABASE+":"+REDIS_KEY_RESOURCE_LIST+":"+adminId;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE+":"+REDIS_KEY_RESOURCE_LIST+":"+adminId;
        redisService.set(key,resourceList,REDIS_EXPIRE);

    }
}

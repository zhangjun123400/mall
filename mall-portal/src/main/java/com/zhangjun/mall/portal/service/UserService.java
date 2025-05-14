package com.zhangjun.mall.portal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjun.mall.model.UmsAdmin;

import java.util.Map;

/**
 * @Author zhangjun
 * @Date 2025/4/28 17:25
 * @Version 1.0
 */
public interface UserService extends IService<UmsAdmin> {

        Map<String,Object> login(UmsAdmin user);
}

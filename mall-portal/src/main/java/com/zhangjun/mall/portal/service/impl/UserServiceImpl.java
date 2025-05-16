package com.zhangjun.mall.portal.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjun.mall.mapper.UmsAdminMapper;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.portal.domain.vo.LoginUser;
import com.zhangjun.mall.portal.service.UserService;
import com.zhangjun.mall.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhangjun
 * @Date 2025/4/28 17:26
 * @Version 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Override
    public Map<String,Object> login(UmsAdmin user) {

        //不需要连接数据库
        //把登陆时候的用户名和密码封装成一个UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());

        //通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authentication)){
           throw new RuntimeException("登陆失败");
        }

        //如果认证成功，就从authentication对象的getPrincipal方法中拿到认证通过后的登陆用户对象
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //生成jwt,使用fastjson的方法，把对象转程字符串
        String loginUserString = JSON.toJSONString(loginUser);

        //生成令牌
        String jwt = jwtUtil.createJWT(loginUserString);

        //jwt的键名
        String toeknKey = "token_"+jwt;

        //存储redis白名单
        redisTemplate.opsForValue().set(toeknKey,jwt, jwtUtil.expiration,TimeUnit.DAYS);


        Map<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("username",loginUser.getUsername());

        return map;
    }




}

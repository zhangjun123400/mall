package com.zhangjun.mall.service.impl;

import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.zhangjun.common.service.RedisService;
import com.zhangjun.mall.dao.UmsAdminRoleRelationDao;
import com.zhangjun.mall.dto.UmsAdminParam;
import com.zhangjun.mall.dto.UpdateAdminPasswordParam;
import com.zhangjun.mall.mapper.UmsAdminLoginLogMapper;
import com.zhangjun.mall.mapper.UmsAdminMapper;
import com.zhangjun.mall.mapper.UmsAdminRoleRelationMapper;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsAdminRoleRelation;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.model.UmsRole;
import com.zhangjun.mall.service.UmsAdminCacheService;
import com.zhangjun.mall.service.UmsAdminService;
import com.zhangjun.mall.utils.JwtTokenUtil;
import com.zhangjun.mall.vo.LoginUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @Author zhangjun
 * @Date 2025/4/28 17:26
 * @Version 1.0
 */
@Service
public class UmsAdminServiceImpl extends ServiceImpl<UmsAdminMapper, UmsAdmin> implements UmsAdminService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UmsAdminMapper umsAdminMapper;

    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;

    @Autowired
    private UmsAdminRoleRelationDao umsAdminRoleRelationDao;

    @Override
    public UmsAdmin getAdminByUsername(String userName){
        LambdaQueryWrapper<UmsAdmin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsAdmin::getUsername, userName);

        return umsAdminMapper.selectOne(queryWrapper);

    }

    @Override
    public Map<String,String> login(UmsAdminParam umsAdminParam) {

        //不需要连接数据库
        //把登陆时候的用户名和密码封装成一个UsernamePasswordAuthenticationToken对象
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(umsAdminParam.getUsername(),umsAdminParam.getPassword());

        //通过AuthenticationManager的authenticate方法来进行用户认证
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        if(Objects.isNull(authentication)){
           throw new RuntimeException("登陆失败");
        }

        //如果认证成功，就从authentication对象的getPrincipal方法中拿到认证通过后的登陆用户对象
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        //生成jwt,使用fastjson的方法，把对象转程字符串
        String loginUserString = JSON.toJSONString(loginUser.getUsername());

        //生成令牌
        String token = jwtTokenUtil.createJWT(loginUserString);

        //jwt的键名
        String tokenKey = "token_"+ token;

        //存储redis白名单
        redisService.set(tokenKey,token);

        Map<String,String> map = new HashMap<>();
        map.put("token",token);
        map.put("username",loginUser.getUsername());

        return map;
    }


    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam,umsAdmin);
        umsAdmin.setCreateTime(LocalDateTimeUtil.now());
        umsAdmin.setStatus(1);

        //查询是否有相同用户名的用户
        LambdaQueryWrapper<UmsAdmin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsAdmin::getUsername, umsAdmin.getUsername());
        UmsAdmin user =umsAdminMapper.selectOne(queryWrapper);
        if (Objects.nonNull(user)) {
            return null;
        }

        //将密码进行加密
        String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());
        umsAdmin.setPassword(encodePassword);
        umsAdminMapper.insert(umsAdmin);

        return umsAdmin;
    }

    @Override
    public String refreshToken(String oldToken) {

        Object redis_token = redisService.get("token_"+oldToken);
        if (!ObjectUtils.isEmpty(redis_token))
        {
            redisService.del("token_"+oldToken);
        }

        String token =  jwtTokenUtil.refreshHeadToken(oldToken);

        //jwt的键名
        String toeknKey = "token_"+token;
        //存储redis白名单
        redisService.set(toeknKey,token);

        return token;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        LambdaQueryWrapper<UmsAdmin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsAdmin::getId, id);

        return umsAdminMapper.selectOne(queryWrapper);

    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<UmsAdmin> queryWrapper = new LambdaQueryWrapper<>();
        if (!StrUtil.isEmpty(keyword))
        {
            queryWrapper.like(UmsAdmin::getUsername,keyword)
                    .or()
                    .like(UmsAdmin::getNickName, keyword);
        }


        return umsAdminMapper.selectList(queryWrapper);
    }

    @Override
    public int update(Long id, UmsAdmin umsAdmin) {
        umsAdmin.setId(id);
        UmsAdmin newAdmin = umsAdminMapper.selectById(id);
        if (umsAdmin.getPassword().equals(newAdmin.getPassword()))
        {
            //与原始密码相同的不需要修改
            umsAdmin.setPassword(null);
        }
        else {
            if (StrUtil.isEmpty(umsAdmin.getPassword())){
                umsAdmin.setPassword(null);
            }
            else {
                umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
            }
        }

        return umsAdminMapper.updateById(umsAdmin);
    }

    @Override
    public int delete(Long id) {
        return umsAdminMapper.deleteById(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {

        int count = roleIds==null?0 :roleIds.size();

        LambdaQueryWrapper<UmsAdminRoleRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UmsAdminRoleRelation::getAdminId, adminId);
        adminRoleRelationMapper.delete(queryWrapper);
        //建立新的关系
        if (!CollectionUtils.isEmpty(roleIds))
        {
            List<UmsAdminRoleRelation> umsAdminRoleRelationList = new ArrayList<>();
            for (Long roleId : roleIds){
                UmsAdminRoleRelation umsAdminRoleRelation = new UmsAdminRoleRelation();
                umsAdminRoleRelation.setAdminId(adminId);
                umsAdminRoleRelation.setRoleId(roleId);
                umsAdminRoleRelationList.add(umsAdminRoleRelation);
                umsAdminRoleRelationDao.insertList(umsAdminRoleRelationList);
        }


        }
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return umsAdminRoleRelationDao.getRoleListByAdminId(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return umsAdminRoleRelationDao.getResourceListByAdminId(adminId);
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam) {
        String oldPassword = updateAdminPasswordParam.getOldPassword();
        String newPassword = updateAdminPasswordParam.getNewPassword();

        //将密码进行加密
        //String encodePassword = passwordEncoder.encode(umsAdmin.getPassword());

        return 0;
    }

    @Override
    public UmsAdminCacheService getCacheService() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        UmsAdmin umsAdmin = getAdminByUsername(username);
        if (umsAdmin !=null){
            List<UmsResource> resourceList = getResourceList(umsAdmin.getId());
            //return new LoginUser(umsAdmin,resourceList);
            return  null;
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public void logout(String username) {

    }
}

package com.zhangjun.mall.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author zhangjun
 * @Date 2025/4/28 15:49
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

   private UmsAdmin umsAdmin;

   //权限列表
   private List<UmsResource> resourceList;//select delete


    //自定义一个权限列表的集合
    @JSONField(serialize=false)
    List<SimpleGrantedAuthority> authorities; //子类

    public LoginUser(UmsAdmin umsAdmin, List<UmsResource> resourceList) {
        this.umsAdmin = umsAdmin;
        this.resourceList = resourceList;
    }
    //权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return resourceList.stream().map(resource ->new SimpleGrantedAuthority(resource.getId()+":"+resource.getName()))
                .collect(Collectors.toList());
    }

    //返回密码
    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    //返回用户名
    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    // 账号是否未过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //账号是否没锁定
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    //账号是否没有超时
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    //账号是否可用
    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}

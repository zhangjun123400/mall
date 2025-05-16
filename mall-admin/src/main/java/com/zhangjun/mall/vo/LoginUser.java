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
   private List<UmsResource> list;//select delete


    //自定义一个权限列表的集合
    @JSONField(serialize=false)
    List<SimpleGrantedAuthority> authorities; //子类

    public LoginUser(UmsAdmin umsAdmin, List<UmsResource> list) {
        this.umsAdmin = umsAdmin;
        this.list = list;
    }
    //权限列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //父类
        if (authorities != null) {
            return authorities;
        }
        authorities = new ArrayList<>();

        for (UmsResource umsResource :list)
        {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(umsResource.getId()+":"+umsResource.getName());
            authorities.add(authority);

        };
        return authorities;
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
        return true;
    }

    //账号是否没有超时
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //账号是否可用
    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}

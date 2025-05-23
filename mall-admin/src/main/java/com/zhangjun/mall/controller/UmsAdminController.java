package com.zhangjun.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.common.service.RedisService;
import com.zhangjun.mall.dto.UmsAdminLoginParam;
import com.zhangjun.mall.dto.UmsAdminParam;
import com.zhangjun.mall.exception.CustomerAuthenticationException;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsRole;
import com.zhangjun.mall.service.UmsRoleService;
import com.zhangjun.mall.service.UmsAdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangjun
 * @Date 2025/4/28 17:23
 * @Version 1.0
 */
@Controller
@Tag(name = "UmsAdminController", description = "后台管理系统")
@RequestMapping("/admin")
public class UmsAdminController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    UmsAdminService umsAdminService;

    @Autowired
    UmsRoleService umsRoleService;

    @Autowired
    RedisService redisService;


    @Operation(summary = "用户注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<? extends UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            return CommonResult.failed("已存在用户，请勿重复注册");
        }
        return CommonResult.success(umsAdmin);
    }

    @Operation(summary = "用户登陆")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
        Map<String,String> map =  umsAdminService.login(umsAdminLoginParam);

        if (!map.get("token").isEmpty() && !map.get("username").isEmpty())
        {
            map.put("tokenHead",tokenHead);
            return CommonResult.success(map,"登陆成功");
        }

        return CommonResult.failed("登陆失败");
    }


    @Operation(summary = "刷新token")
    @RequestMapping(value = "/refreshToken",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = umsAdminService.refreshToken(token);
        Map<String,Object> map = new HashMap<>();

        if (ObjectUtils.isEmpty(refreshToken)) {
            return CommonResult.failed("token已经过期！");
        }

        map.put("token",refreshToken);
        return CommonResult.success(map);


    }


    @Operation(summary = "获取当前登录用户信息")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal){
        if (principal==null){
            return CommonResult.unauthorized(null);
        }

        String userName = principal.getName();
        UmsAdmin umsAdmin = umsAdminService.getAdminByUsername(userName);

        Map<String, Object> data = new HashMap<>();

        data.put("username",umsAdmin.getUsername());
        data.put("menus",umsRoleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = umsAdminService.getRoleList(umsAdmin.getId());
        if (CollUtil.isNotEmpty(roleList))
        {
            List<String> roles = roleList.stream().map(UmsRole::getName).toList();
            data.put("roles",roles);
        }

        return CommonResult.success(data);
    }


    @Operation(summary = "用户登出")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response)
    {

        //在logout中要获取jwt
        String token = request.getHeader("Authorization");
        if(ObjectUtils.isEmpty(token)){
            token = request.getParameter("token");
        }
        if(ObjectUtils.isEmpty(token)){
            throw new CustomerAuthenticationException("token为空");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            //1、清除上下文
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            //2、清除Redis
            redisService.del("token_"+token);

        }

        return CommonResult.success("用户退出成功");
    }

    @Operation(summary = "根据用户名或者姓名分页获取用户列表")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword",required = false) String keyword,
                                                   @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = umsAdminService.list(keyword,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @Operation(summary = "删除指定用户信息")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id){
        int count = umsAdminService.delete(id);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();

    }

    @Operation(summary = "修改账号状态")
    @RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,@RequestParam(value = "status") Integer status ) {
        UmsAdmin umsAdmin =new UmsAdmin();
        umsAdmin.setStatus(status);
        int count = umsAdminService.update(id,umsAdmin);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId){
        List<UmsRole> umsRoleList= umsAdminService.getRoleList(adminId);
        return CommonResult.success(umsRoleList);
    }


    @Operation(summary = "给用户分配角色")
    @RequestMapping(value = "/role/update",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds){
       int count = umsAdminService.updateRole(adminId,roleIds);
       if (count>0)
       {
           return CommonResult.success(count);
       }

       return CommonResult.failed();
    }


    @Operation(summary = "获取指定用户信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id){
        UmsAdmin umsAdmin =umsAdminService.getItem(id);
        return CommonResult.success(umsAdmin);
    }

    @Operation(summary = "修改制定用户信息")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,@RequestBody UmsAdminParam umsAdminParam) {
        UmsAdmin umsAdmin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam,umsAdmin);
        int count = umsAdminService.update(id,umsAdmin);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }



}

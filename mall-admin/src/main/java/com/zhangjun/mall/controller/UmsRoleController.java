package com.zhangjun.mall.controller;

import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mall.model.UmsMenu;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.model.UmsResourceCategory;
import com.zhangjun.mall.model.UmsRole;
import com.zhangjun.mall.service.UmsRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 22:07
 * @Version 1.0
 */
@RestController
@Tag(name = "UmsRoleController",description = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {

    @Autowired
    private UmsRoleService umsRoleService;

    @Operation(summary = "添加角色")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsRole umsRole){
        int count = umsRoleService.create(umsRole);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "修改角色")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsRole umsRole) {
        int count = umsRoleService.update(id,umsRole);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "批量删除角色")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public CommonResult delete(@RequestParam("ids") List<Long> ids){
        int count = umsRoleService.delete(ids);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @Operation(summary = "获取所有角色")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public CommonResult<List<UmsRole>> listAll(){
        List<UmsRole> roleList = umsRoleService.list();
        return CommonResult.success(roleList);
    }

    @Operation(summary = "根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword",required = false) String keywprd,
                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<UmsRole> umsRoleList = umsRoleService.list(keywprd,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(umsRoleList));

    }


    @Operation(summary = "修改角色状态")
    @RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.POST)
    public CommonResult updateStatus(@PathVariable Long id,@RequestParam(value = "status") Integer status){
        UmsRole umsRole = new UmsRole();
        umsRole.setStatus(status);
        int count = umsRoleService.update(id,umsRole);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "获取角色的想过菜单")
    @RequestMapping(value = "/listMenu/{roleId}",method = RequestMethod.GET)
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable Long roleId){
            List<UmsMenu> menuList = umsRoleService.listMenu(roleId);
            return CommonResult.success(menuList);
    }


    @Operation(summary = "获取角色相关资源")
    @RequestMapping(value = "/listResource/{roleId}",method = RequestMethod.GET)
    public CommonResult<List<UmsResource>> listResource(@PathVariable Long roleId){
        List<UmsResource> resourceList = umsRoleService.listResource(roleId);
        return CommonResult.success(resourceList);
    }

    @Operation(summary = "给角色分配菜单")
    @RequestMapping(value = "/allocMenu",method = RequestMethod.POST)
    public CommonResult allocMenu(@RequestParam Long roleId,@RequestParam List<Long> menuIds){
        int count = umsRoleService.allocMenu(roleId,menuIds);
        return CommonResult.success(count);
    }


    @Operation(summary = "给角色分配资源")
    @RequestMapping(value = "/allocResource",method = RequestMethod.POST)
    public CommonResult allocResource(@RequestParam Long roleId,@RequestParam List<Long> resourceIds){
        int count = umsRoleService.allocResource(roleId,resourceIds);
        return CommonResult.success(count);
    }
}



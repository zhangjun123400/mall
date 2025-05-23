package com.zhangjun.mall.controller;

import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mall.dto.UmsMenuNode;
import com.zhangjun.mall.model.UmsMenu;
import com.zhangjun.mall.service.UmsMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 23:36
 * @Version 1.0
 */
@RestController
@Tag(name = "UmsMenuController",description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {

    @Autowired
    private UmsMenuService umsMenuService;

    @Operation(summary = "添加后台菜单")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsMenu umsMenu){
        int count = umsMenuService.create(umsMenu);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @Operation(summary = "修改后台菜单")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsMenu umsMenu){
        int count = umsMenuService.update(id,umsMenu);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "根据ID获取菜单详情")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult<UmsMenu> getItem(@PathVariable Long id){
            UmsMenu umsMenu = umsMenuService.getItem(id);
            return CommonResult.success(umsMenu);
    }

    @Operation(summary = "根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id){
        int count = umsMenuService.delete(id);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @Operation(summary = "分页查询后台菜单")
    @RequestMapping(value = "/list/{parentId}",method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<UmsMenu> umsMenuList = umsMenuService.list(parentId,pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(umsMenuList));
    }

    @Operation(summary = "树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public CommonResult<List<UmsMenuNode>> treeList(){
        List<UmsMenuNode> umsMenuNodeList = umsMenuService.treeList();
        return CommonResult.success(umsMenuNodeList);
    }

    @Operation(summary = "修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    public CommonResult updateHidden(@PathVariable Long id, @RequestParam("hidden") Integer hidden) {
        int count = umsMenuService.updateHidden(id, hidden);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }
}

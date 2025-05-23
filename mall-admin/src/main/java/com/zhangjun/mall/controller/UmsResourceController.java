package com.zhangjun.mall.controller;

import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mall.handler.DynamicSecurityMetadataSource;
import com.zhangjun.mall.model.UmsAdmin;
import com.zhangjun.mall.model.UmsResource;
import com.zhangjun.mall.service.UmsResourceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 20:43
 * @Version 1.0
 */
@RestController
@Tag(name = "UmsResourceController",description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService umsResourceService;

    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;



    @Operation(summary = "添加后台资源")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsResource umsResource){
        int count = umsResourceService.create(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "修改资源")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResource umsResource){
        int count  = umsResourceService.update(id,umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


    @Operation(summary = "根据ID获取资源详情")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult<UmsResource> getItem(@PathVariable Long id){
        UmsResource umsResource = umsResourceService.getItem(id);
        return CommonResult.success(umsResource);
    }

    @Operation(summary = "根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id){
        int count = umsResourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();

    }

    @Operation(summary = "分页模糊查询后台资源")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
            List<UmsResource> resourceList= umsResourceService.list(categoryId,nameKeyword,urlKeyword,pageSize,pageNum);
            return CommonResult.success(CommonPage.restPage(resourceList));
    }


    @Operation(summary = "查询所有后台资源")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public CommonResult<List<UmsResource>> listAll(){
        List<UmsResource> resourceList = umsResourceService.listAll();
        return CommonResult.success(resourceList);
    }
}

package com.zhangjun.mall.controller;

import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mall.model.UmsResourceCategory;
import com.zhangjun.mall.service.UmsResourceCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/5/23 21:10
 * @Version 1.0
 */
@RestController
@Tag(name = "UmsResourceCategoryController",description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {

    private UmsResourceCategoryService umsResourceCategoryService;


    @Operation(summary = "查询所有后台资源分类")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public CommonResult<List<UmsResourceCategory>> listAll(){
        List<UmsResourceCategory> resourceCategoryList = umsResourceCategoryService.listAll();
        return CommonResult.success(resourceCategoryList);
    }


    @Operation(summary = "添加后台资源分类")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsResourceCategory umsResourceCategory){
        int count = umsResourceCategoryService.create(umsResourceCategory);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();

    }

    @Operation(summary = "修改后台资源分类")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory umsResource){
        int count = umsResourceCategoryService.update(id,umsResource);
        if (count>0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @Operation(summary = "根据ID删除后台资源分类")
    @RequestMapping(value = "/list/{id}",method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id){
        int count = umsResourceCategoryService.delete(id);
        if (count>0)
        {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }
}

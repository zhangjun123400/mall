package com.zhangjun.mall.controller;

import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mall.dto.PmsProductAttributeCategoryItem;
import com.zhangjun.mall.dto.PmsProductAttributeCategoryParam;
import com.zhangjun.mall.model.PmsProductAttributeCategory;
import com.zhangjun.mall.service.PmsProductAttributeCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author zhangjun
 * @Date 2025/6/18 23:39
 * @Version 1.0
 */
@RestController
@Tag(name ="PmsProductAttributeCategoryController" ,description = "商品属性分类管理")
@RequestMapping("/productAttribute/category")
public class PmsProductAttributeCategoryController {

    @Autowired
    private PmsProductAttributeCategoryService pmsProductAttributeCategoryService;

    @Operation(summary = "添加商品属性分类")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult create(@RequestParam String name) {
        int count = pmsProductAttributeCategoryService.create(name);
        if (count > 0) {
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "修改商品属性分类")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestParam String name) {
        int count = pmsProductAttributeCategoryService.update(id,name);
        if (count > 0) {
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "删除单个商品属性分类")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public CommonResult delete(@PathVariable Long id) {
        int count = pmsProductAttributeCategoryService.delete(id);
        if (count > 0) {
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "获取单个商品属性分类信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult<PmsProductAttributeCategory> getItem(@PathVariable Long id) {
        PmsProductAttributeCategory pmsProductAttributeCategory = pmsProductAttributeCategoryService.getItem(id);
        return CommonResult.success(pmsProductAttributeCategory);

    }

    @Operation(summary = "分页获取所有商品属性分类")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttributeCategory>> getList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam(defaultValue = "5") int pageSize) {
        List<PmsProductAttributeCategory> pmsProductAttributeCategoryList= pmsProductAttributeCategoryService.getList(pageSize,pageNum);
        return CommonResult.success(CommonPage.restPage(pmsProductAttributeCategoryList));
    }

    @Operation(summary = "获取所有商pub品属性分类及其下属性")
    @RequestMapping(value = "/list/withAttr",method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsProductAttributeCategoryItem>> getListWithAttr(){
        List<PmsProductAttributeCategoryItem> pmsProductAttributeCategoryItemList= pmsProductAttributeCategoryService.getListWithAttr();
        return CommonResult.success(CommonPage.restPage(pmsProductAttributeCategoryItemList));
    }
}

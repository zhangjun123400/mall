package com.zhangjun.mall.controller;

import com.zhangjun.common.api.CommonPage;
import com.zhangjun.common.api.CommonResult;
import com.zhangjun.mall.dto.PmsBrandParam;
import com.zhangjun.mall.model.PmsBrand;
import com.zhangjun.mall.service.PmsBrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品品牌管理Controller
 * @Author zhangjun
 * @Date 2025/6/17 23:29
 * @Version 1.0
 */
@RestController
@Tag(name = "PmsBrandController",description = "商品品牌管理")
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService pmsBrandService;

    @Operation(summary = "获取全部品牌列表")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    public CommonResult<List<PmsBrand>> listAllBrand() {
            return CommonResult.success(pmsBrandService.listAll());
    }

    @Operation(summary = "添加品牌")
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public CommonResult createBrand(@Validated @RequestBody PmsBrandParam pmsBrandParam) {
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrandParam);
        if (count > 0) {
            commonResult = CommonResult.success(count);
        }else{
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }


    @Operation(summary = "更新品牌")
    @RequestMapping(value = "/update/{id}",method = RequestMethod.POST)
    public CommonResult updateBrand(@PathVariable("id") Long id,@Validated @RequestBody PmsBrandParam pmsBrandParam) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id,pmsBrandParam);
        if (count > 0) {
            commonResult = CommonResult.success(count);
        }else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }


    @Operation(summary = "删除品牌")
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.POST)
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        CommonResult commonResult;
        int count = pmsBrandService.deleteBrand(id);
        if (count > 0) {
            commonResult = CommonResult.success(count);
        }else {
            commonResult = CommonResult.failed();
        }
        return commonResult;
    }

    @Operation(summary = "分页获取品牌列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public CommonResult<CommonPage<PmsBrand>> listPage(@RequestParam(value = "keyword",required = false) String keyword,
                                                       @RequestParam(value = "showStatus",required = false) Integer showStatus ,
                                                       @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                       @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize) {
        List<PmsBrand> pmsBrandList = pmsBrandService.listBrand(keyword,showStatus,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(pmsBrandList));

    }

    @Operation(summary = "根据编号查询品牌信息")
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public CommonResult getItemList(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }


    @Operation(summary = "批量删除品牌")
    @RequestMapping(value = "/delete/batch",method = RequestMethod.POST)
    public CommonResult deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = pmsBrandService.deleteBrand(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "批量更新显示状态")
    @RequestMapping(value = "/update/showStatus",method = RequestMethod.POST)
    public CommonResult updateShowStatus(@RequestParam("ids") List<Long> ids,@RequestParam("showStatus") Integer showStatus) {
        int count = pmsBrandService.updateShowStatus(ids,showStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }else {
            return CommonResult.failed();
        }
    }

    @Operation(summary = "批量更新厂家制造商状态")
    @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
    public CommonResult updateFactoryStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("factoryStatus") Integer factoryStatus) {
        int count = pmsBrandService.updateFactoryStatus(ids, factoryStatus);
        if (count > 0) {
            return CommonResult.success(count);
        } else {
            return CommonResult.failed();
        }
    }

}

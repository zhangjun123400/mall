package com.zhangjun.mall.service;

import com.zhangjun.mall.dto.PmsBrandParam;
import com.zhangjun.mall.model.PmsBrand;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 商品品牌管理Service
 * @Author zhangjun
 * @Date 2025/6/17 23:02
 * @Version 1.0
 */
public interface PmsBrandService {

    /**
     * 获取所有品牌
     * @return
     */
    public List<PmsBrand> listAll();

    /**
     * 创建品牌
     * @param pmsBrandParam
     * @return
     */
    int createBrand(PmsBrandParam pmsBrandParam);

    /**
     * 修改品牌
     * @param id
     * @param pmsBrandParam
     * @return
     */
    @Transactional
    int updateBrand(Long id, PmsBrandParam pmsBrandParam);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    int deleteBrand(Long id);

    /**
     * 批量删除品牌
     * @param ids
     * @return
     */
    int deleteBrand(List<Long> ids);
    /**
     * 分页查询品牌
     * @param keyword
     * @param showStatus
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PmsBrand> listBrand(String keyword,Integer showStatus,Integer pageNum, Integer pageSize);

    /**
     * 获取品牌详情
     * @param id
     * @return
     */
    PmsBrand getBrand(Long id);

    /**
     * 修改显示状态
     * @param id
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> id, Integer showStatus);


    /**
     * 修改厂家制造商状态
     * @param id
     * @param factoryStatus
     * @return
     */
    int updateFactoryStatus(List<Long> id, Integer factoryStatus);

}

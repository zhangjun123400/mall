package com.zhangjun.mall;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangjun.mall.mapper.PmsBrandMapper;
import com.zhangjun.mall.model.PmsBrand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MallMbgApplication.class)
class MallMbgApplicationTests {

    @Autowired
    PmsBrandMapper pmsBrandMapper;

    @Test
    void contextLoads() {

       List<PmsBrand> list =  pmsBrandMapper.selectList(new QueryWrapper<>());

       for (PmsBrand pmsBrand : list)
       {
           System.out.println(pmsBrand.getName());
       }


    }

}

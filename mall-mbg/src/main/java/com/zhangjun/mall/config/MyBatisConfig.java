package com.zhangjun.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * @Author zhangjun
 * @Date 2025/4/22 17:26
 * @Version 1.0
 */
@Configuration
@MapperScan("com.zhangjun.mall.mapper")
public class MyBatisConfig {
}

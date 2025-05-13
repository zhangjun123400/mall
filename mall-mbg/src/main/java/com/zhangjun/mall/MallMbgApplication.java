package com.zhangjun.mall;

import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {MybatisAutoConfiguration.class})
public class MallMbgApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallMbgApplication.class, args);


    }

}

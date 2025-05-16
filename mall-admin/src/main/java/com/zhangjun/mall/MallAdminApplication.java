package com.zhangjun.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.zhangjun.mall",
		"com.zhangjun.common" // 添加 common 模块的扫描
})
public class MallAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallAdminApplication.class, args);
	}

}

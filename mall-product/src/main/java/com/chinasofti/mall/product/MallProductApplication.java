package com.chinasofti.mall.product;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.chinasofti.mall.product.dao")
public class MallProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallProductApplication.class, args);
    }
}

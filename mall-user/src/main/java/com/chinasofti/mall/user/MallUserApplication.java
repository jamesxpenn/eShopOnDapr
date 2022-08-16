package com.chinasofti.mall.user;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.chinasofti.mall.user.dao")
public class MallUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallUserApplication.class, args);
    }
}

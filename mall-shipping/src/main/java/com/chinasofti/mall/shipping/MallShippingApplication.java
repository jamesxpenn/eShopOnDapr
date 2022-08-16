package com.chinasofti.mall.shipping;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.chinasofti.mall.shipping.dao")
public class MallShippingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallShippingApplication.class, args);
    }
}

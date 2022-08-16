package com.chinasofti.mall.order;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@MapperScan(basePackages = "com.chinasofti.mall.order.dao")
public class MallOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallOrderApplication.class, args);
    }
}

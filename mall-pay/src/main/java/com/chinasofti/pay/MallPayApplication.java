package com.chinasofti.pay;


import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDiscoveryClient
public class MallPayApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallPayApplication.class, args);
    }
}

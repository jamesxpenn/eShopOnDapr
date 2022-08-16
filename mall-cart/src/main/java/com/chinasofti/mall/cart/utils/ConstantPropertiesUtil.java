package com.chinasofti.mall.cart.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {

//    @Value("${spring.application.name}")
//    private String appName;

    public static String APP_NAME= "mallcart";

    @Override
    public void afterPropertiesSet() throws Exception {
       // APP_NAME=appName;
    }
}

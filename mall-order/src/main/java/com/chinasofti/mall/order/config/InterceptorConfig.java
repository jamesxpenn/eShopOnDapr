package com.chinasofti.mall.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by xuepeng@chinasofti.com
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new com.chinasofti.mall.order.intercepter.UserLoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/orders/uoid/**");
	}
}

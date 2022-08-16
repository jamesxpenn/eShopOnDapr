package com.chinasofti.mall.shipping.config;

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
		registry.addInterceptor(new com.chinasofti.mall.shipping.intercepter.UserLoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/shippings/usid/**", "/user/login","/shippings/idset/**","/shippings/shipid/**");
	}
}

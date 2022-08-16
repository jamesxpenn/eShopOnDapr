package com.chinasofti.mall.cart.config;

import com.chinasofti.mall.cart.intercepter.CartLoginInterceptor;
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
		registry.addInterceptor(new CartLoginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/carts/listcards/**","/carts/userid/**");
	}
}

package com.longmao.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.longmao.demo.filter.TestFilter;
import com.longmao.demo.interceptor.UriInterceptor;

@Configuration
@AutoConfigureAfter({ WebMvcAutoConfiguration.class })
public class WebMveConfig implements WebMvcConfigurer {
	@Autowired
	private UriInterceptor uriInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(uriInterceptor).addPathPatterns("/**");
		WebMvcConfigurer.super.addInterceptors(registry);
	}

	@Bean
	public FilterRegistrationBean<TestFilter> testFilter() {
		FilterRegistrationBean<TestFilter> bean = new FilterRegistrationBean<TestFilter>();
		bean.setFilter(new TestFilter());
		return bean;
	}
}

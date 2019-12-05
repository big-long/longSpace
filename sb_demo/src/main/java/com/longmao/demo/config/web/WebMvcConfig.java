package com.longmao.demo.config.web;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.longmao.demo.filter.TestFilter;
import com.longmao.demo.interceptor.UriInterceptor;

@Configuration
@AutoConfigureAfter({ WebMvcAutoConfiguration.class })
public class WebMvcConfig implements WebMvcConfigurer {
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
//	@Bean
//	 public MultipartConfigElement  multipartConfigElement() {
//		MultipartConfigFactory factory=new MultipartConfigFactory() ;
//		factory.setMaxFileSize("100MB");
//		 return factory.createMultipartConfig();
//	 }
}

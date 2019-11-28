package com.longmao.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.longmao.demo.modules.account.dao")
public class SbDemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication=new SpringApplication(SbDemoApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		SpringApplication.run(SbDemoApplication.class, args);
		
	}

}

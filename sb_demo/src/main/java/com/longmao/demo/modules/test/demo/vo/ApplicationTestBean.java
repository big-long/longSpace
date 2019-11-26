package com.longmao.demo.modules.test.demo.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:applicationTest.properties")
@ConfigurationProperties(prefix="com.test")
public class ApplicationTestBean {
	private String name;
	private int  age;
	private String description;
	private String random;
	public ApplicationTestBean(String name, int age, String description, String random) {
		super();
		this.name = name;
		this.age = age;
		this.description = description;
		this.random = random;
	}
	public ApplicationTestBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRandom() {
		return random;
	}
	public void setRandom(String random) {
		this.random = random;
	}
	@Override
	public String toString() {
		return "ApplicationTestBean [name=" + name + ", age=" + age + ", description=" + description + ", random="
				+ random + "]";
	}
		

}

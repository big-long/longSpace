package com.longmao.demo.modules.test.entity;

public class Average {
	private int value;
	private String name;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Average(int value, String name) {
		super();
		this.value = value;
		this.name = name;
	}
	public Average() {
		super();
	}
	@Override
	public String toString() {
		return "Average [value=" + value + ", name=" + name + "]";
	}
	
}

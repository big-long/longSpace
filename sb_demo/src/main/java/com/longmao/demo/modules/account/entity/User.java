package com.longmao.demo.modules.account.entity;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


public class User {
	private int userId;
	private String userName;
	private String password;
	@JsonFormat(pattern = "yyyy‐MM‐dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy‐MM‐dd HH:mm:ss")
	private Date createDate;
//	private List<Role> roles;
	public User() {
		super();
	}
	public User(int userId, String userName, String password, Date createDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createDate = createDate;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
//	public List<Role> getRoles() {
//		return roles;
//	}
//	public void setRoles(List<Role> roles) {
//		this.roles = roles;
//	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", createDate="
				+ createDate + "]";
	}
	
	
	
}

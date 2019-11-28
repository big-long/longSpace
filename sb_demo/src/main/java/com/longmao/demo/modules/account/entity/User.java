package com.longmao.demo.modules.account.entity;

import java.util.Date;


public class User {
	private int userId;
	private String userName;
	private String password;
	private Date createDate;
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", password=" + password + ", createDate="
				+ createDate + "]";
	}
	
	
	
}

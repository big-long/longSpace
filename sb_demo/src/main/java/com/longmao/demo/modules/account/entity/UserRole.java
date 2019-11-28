package com.longmao.demo.modules.account.entity;


public class UserRole {
	private int userRoleId;
	private int userId;
	private int roleId;
	public UserRole() {
		super();
	}
	public UserRole(int userId, int roleId,int userRoleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
		this.userRoleId=userRoleId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	@Override
	public String toString() {
		return "UserRole [userRoleId=" + userRoleId + ", userId=" + userId + ", roleId=" + roleId + "]";
	}
	
	
}

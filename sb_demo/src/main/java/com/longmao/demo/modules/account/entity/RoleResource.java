package com.longmao.demo.modules.account.entity;

public class RoleResource {
	private int roleResourceId;
	private int roleId;
	private int resourceId;
	public RoleResource() {
		super();
	}
	public RoleResource(int roleId, int resourceId,int roleResourceId) {
		super();
		this.roleId = roleId;
		this.resourceId = resourceId;
		this.roleResourceId=roleResourceId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	
	public int getRoleResourceId() {
		return roleResourceId;
	}
	public void setRoleResourceId(int roleResourceId) {
		this.roleResourceId = roleResourceId;
	}
	@Override
	public String toString() {
		return "RoleResource [roleResourceId=" + roleResourceId + ", roleId=" + roleId + ", resourceId=" + resourceId
				+ "]";
	}

	
}

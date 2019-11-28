package com.longmao.demo.modules.account.entity;

import java.util.Date;


public class Role {
	private int roleId;
	private String roleName;
	private String name;
	private Date createDate;

	public Role() {
		super();
	}

	public Role(int roleId, String roleName, String name, Date createDate) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.name = name;
		this.createDate = createDate;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", name=" + name + ", createDate=" + createDate
				+ "]";
	}

}

package com.longmao.demo.modules.account.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Resource {
	private int resourceId;
	private String resourceUrl;
	private String resourceName;
	private String permission;
	@JsonFormat(pattern = "yyyy‐MM‐dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy‐MM‐dd HH:mm:ss")
	private Date createDate;
	public Resource(int resourceId, String resourceUrl, String resourceName, String permission, Date createDate) {
		super();
		this.resourceId = resourceId;
		this.resourceUrl = resourceUrl;
		this.resourceName = resourceName;
		this.permission = permission;
		this.createDate = createDate;
	}
	public Resource() {
		super();
	}
	public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	public String getResourceUrl() {
		return resourceUrl;
	}
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "Resource [resourceId=" + resourceId + ", resourceUrl=" + resourceUrl + ", resourceName=" + resourceName
				+ ", permission=" + permission + ", createDate=" + createDate + "]";
	}
	
}

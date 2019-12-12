package com.longmao.demo.modules.account.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "m_resource")
public class Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resourceId;
	private String resourceUrl;
	private String resourceName;
	private String permission;
	private int parentId;
	private int status;
	@JsonFormat(pattern = "yyyy‐MM‐dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy‐MM‐dd HH:mm:ss")
	private Date createDate;

	public Resource(int resourceId, String resourceUrl, String resourceName, String permission, int parentId,
			int status, Date createDate) {
		super();
		this.resourceId = resourceId;
		this.resourceUrl = resourceUrl;
		this.resourceName = resourceName;
		this.permission = permission;
		this.parentId = parentId;
		this.status = status;
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

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
				+ ", permission=" + permission + ", parentId=" + parentId + ", status=" + status + ", createDate="
				+ createDate + "]";
	}
}

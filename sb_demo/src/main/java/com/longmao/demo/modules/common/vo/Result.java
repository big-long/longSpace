package com.longmao.demo.modules.common.vo;

public class Result {
	private int status;
	private String message;
	private Object object;
	public Result() {
		super();
	}
	public Result(int status, String message, Object object) {
		super();
		this.status = status;
		this.message = message;
		this.object = object;
	}
	public Result(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	@Override
	public String toString() {
		return "Result [status=" + status + ", message=" + message + ", object=" + object + "]";
	}
	
}

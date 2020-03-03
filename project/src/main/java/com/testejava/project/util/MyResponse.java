package com.testejava.project.util;

public class MyResponse<T> {

	private Boolean success;
	private String message;
	private T data;
	
	public MyResponse(){
		success = false;
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

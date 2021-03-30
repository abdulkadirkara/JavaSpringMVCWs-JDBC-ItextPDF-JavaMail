package com.adoptez1plumbier.util;

import java.io.Serializable;




public class ServiceResponse<T> implements Serializable{
	
	
	private static final long serialVersionUID = -5664625943292111254L;
	private String message = null;
//	private String code = ErrorType.SUCCESS.getCode();
	private T data = null;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
//	public String getCode() {
//		return code;
//	}
//	public void setCode(String code) {
//		this.code = code;
//	}

}
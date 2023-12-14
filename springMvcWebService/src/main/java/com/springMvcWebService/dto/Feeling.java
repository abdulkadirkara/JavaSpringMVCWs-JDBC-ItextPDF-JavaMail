package com.springMvcWebService.dto;

import java.io.Serializable;

public class Feeling implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private long uid;
	private String fieldname;
	private int fieldfeeling;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public int getFieldfeeling() {
		return fieldfeeling;
	}
	public void setFieldfeeling(int fieldfeeling) {
		this.fieldfeeling = fieldfeeling;
	}

}

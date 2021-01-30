package com.adoptez1plumbier.dto;

import java.io.Serializable;
import java.util.Date;

public class Palace implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private long uid;
	private Date date;
	private String fieldName;
	private String fieldInput;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldInput() {
		return fieldInput;
	}
	public void setFieldInput(String fieldInput) {
		this.fieldInput = fieldInput;
	}
	
	

}

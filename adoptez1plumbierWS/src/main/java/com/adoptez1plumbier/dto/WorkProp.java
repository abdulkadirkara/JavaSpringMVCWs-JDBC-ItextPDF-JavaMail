package com.adoptez1plumbier.dto;

import java.io.Serializable;

public class WorkProp implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private long uid;
	private String fieldName;
	private int fieldNumberSection;
	private boolean fieldNumberSecondSection;
	private String feildsImageSecondSection;
	
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
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public int getFieldNumberSection() {
		return fieldNumberSection;
	}
	public void setFieldNumberSection(int fieldNumberSection) {
		this.fieldNumberSection = fieldNumberSection;
	}
	public boolean isFieldNumberSecondSection() {
		return fieldNumberSecondSection;
	}
	public void setFieldNumberSecondSection(boolean fieldNumberSecondSection) {
		this.fieldNumberSecondSection = fieldNumberSecondSection;
	}
	public String getFeildsImageSecondSection() {
		return feildsImageSecondSection;
	}
	public void setFeildsImageSecondSection(String feildsImageSecondSection) {
		this.feildsImageSecondSection = feildsImageSecondSection;
	}
	
	
}

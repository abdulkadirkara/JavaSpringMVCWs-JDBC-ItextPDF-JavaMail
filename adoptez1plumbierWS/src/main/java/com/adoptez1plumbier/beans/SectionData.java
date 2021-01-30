package com.adoptez1plumbier.beans;

import java.io.Serializable;
import java.util.Date;

public class SectionData implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private User userId;
	private long sid;
	private long sectionId;
	private String date;
	private long pdfId;
	private String fieldName;
	private String fieldInput;
	private int fieldNumberFirstSection;
	private boolean fieldIsNumberSecondSection;
	private String fieldsImageSecondSection;
	private int fieldsFeeling;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public int getFieldNumberFirstSection() {
		return fieldNumberFirstSection;
	}

	public void setFieldNumberFirstSection(int fieldNumberFirstSection) {
		this.fieldNumberFirstSection = fieldNumberFirstSection;
	}

	public boolean getIsFieldIsNumberSecondSection() {
		return fieldIsNumberSecondSection;
	}

	public void setFieldIsNumberSecondSection(boolean fieldIsNumberSecondSection) {
		this.fieldIsNumberSecondSection = fieldIsNumberSecondSection;
	}

	public String getFieldsImageSecondSection() {
		return fieldsImageSecondSection;
	}

	public void setFieldsImageSecondSection(String fieldsImageSecondSection) {
		this.fieldsImageSecondSection = fieldsImageSecondSection;
	}

	public int getFieldsFeeling() {
		return fieldsFeeling;
	}

	public void setFieldsFeeling(int fieldsFeeling) {
		this.fieldsFeeling = fieldsFeeling;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

	public long getPdfId() {
		return pdfId;
	}

	public void setPdfId(long pdfId) {
		this.pdfId = pdfId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getSid() {
		return sid;
	}

	public void setSid(long sid) {
		this.sid = sid;
	}

}

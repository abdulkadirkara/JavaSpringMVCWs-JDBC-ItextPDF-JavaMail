package com.springMvcWebService.beans;

import java.io.Serializable;
import java.util.List;

public class Section implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String sectionName;
	private int sectionType;
	private String signatureData;
	private String signatureBaseData;
	private List<SectionData> sectionData;


	public String getSignatureData() {
		return signatureData;
	}

	public void setSignatureData(String signatureData) {
		this.signatureData = signatureData;
	}

	public String getSignatureBaseData() {
		return signatureBaseData;
	}

	public void setSignatureBaseData(String signatureBaseData) {
		this.signatureBaseData = signatureBaseData;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public int getSectionType() {
		return sectionType;
	}

	public void setSectionType(int sectionType) {
		this.sectionType = sectionType;
	}

	public List<SectionData> getSectionData() {
		return sectionData;
	}

	public void setSectionData(List<SectionData> sectionData) {
		this.sectionData = sectionData;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}

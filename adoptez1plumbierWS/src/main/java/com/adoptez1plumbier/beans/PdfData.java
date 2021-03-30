package com.adoptez1plumbier.beans;

import java.util.Date;

public class PdfData {
	
	private long id;
	private String pdfid;
	private User uid;
	private long workId;
	private Date date;
	private String name;
	private String signature;
	private byte[] pdf;
	
	
	
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUid() {
		return uid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setUid(User uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getPdf() {
		return pdf;
	}
	public void setPdf(byte[] pdf) {
		this.pdf = pdf;
	}
	public long getWorkId() {
		return workId;
	}
	public void setWorkId(long workId) {
		this.workId = workId;
	}
	public String getPdfid() {
		return pdfid;
	}
	public void setPdfid(String pdfid) {
		this.pdfid = pdfid;
	}
	
	

}

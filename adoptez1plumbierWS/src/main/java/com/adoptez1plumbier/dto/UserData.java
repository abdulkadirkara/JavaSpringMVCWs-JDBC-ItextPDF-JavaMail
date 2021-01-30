package com.adoptez1plumbier.dto;

import java.io.Serializable;
import java.util.List;

import com.adoptez1plumbier.beans.Section;
import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;



public class UserData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long uid;
	private User userInformation;
	private List<SectionData> userWorks;
	
	
	public User getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(User userInformation) {
		this.userInformation = userInformation;
	}
	public List<SectionData> getUserWorks() {
		return userWorks;
	}
	public void setUserWorks(List<SectionData> userWorks) {
		this.userWorks = userWorks;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
}

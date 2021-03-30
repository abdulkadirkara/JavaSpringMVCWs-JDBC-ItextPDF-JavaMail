package com.adoptez1plumbier.dto;
import java.io.Serializable;
import java.util.List;
import com.adoptez1plumbier.beans.User;



public class UserData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long uid;
	private User userInformation;
//	private List<SectionData> datas;
	private List<UserWork> userWorks;
	
//	public List<SectionData> getDatas() {
//		return datas;
//	}
//	public void setDatas(List<SectionData> datas) {
//		this.datas = datas;
//	}
	
	public User getUserInformation() {
		return userInformation;
	}
	public void setUserInformation(User userInformation) {
		this.userInformation = userInformation;
	}
	public List<UserWork> getUserWorks() {
		return userWorks;
	}
	public void setUserWorks(List<UserWork> userWorks) {
		this.userWorks = userWorks;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	
}

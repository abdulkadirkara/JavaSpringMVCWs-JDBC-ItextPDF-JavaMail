package com.springMvcWebService.beans;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String phone;
	private String username;
	private String password;
	private String profileImg;
	private int isAdmin;
	
	public User() {
		
		
	}

	public User(long id, String name, String phone, String username, String password,String profileImg,int isAdmin) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.profileImg=profileImg;
		this.isAdmin= isAdmin;
	}
	
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

}

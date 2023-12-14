package com.springMvcWebService.util;

public enum Type {
  BIEN(0, 0,""),
  MOYEN(1, 1,""),
  MAVIUS(2, 2,"");
	
  
  private int type;
  private int code;
  private String message;
  
  Type(int type, int code, String message) {
    this.type = type;
    this.code = code;
    this.message=message;
  }
  
  public int getType() {
    return this.type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public int getCode() {
    return this.code;
  }
  
  public void setCode(int code) {
    this.code = code;
  }

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
}

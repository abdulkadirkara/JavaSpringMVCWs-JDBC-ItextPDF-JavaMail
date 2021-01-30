package com.adoptez1plumbier.util;

public enum OuiNon {
	OUI(1,1,"OUI"),
	NON(0,0,"NON"),
	NOMBRETRUE(1,1,"1"),
	NOMBREFALSE(0,0," ");

	
	private int type;
	private String message;
  	private int code;
  
  OuiNon(int type, int code, String message) {
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

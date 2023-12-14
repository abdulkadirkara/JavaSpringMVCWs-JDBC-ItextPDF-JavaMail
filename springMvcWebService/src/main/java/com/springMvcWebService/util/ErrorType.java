package com.springMvcWebService.util;

public enum ErrorType {
  
	
  SUCCESS("S", "0000", "Success"),
  ERROR("E", "0001", "Error"),
  FAIL("F", "0002", "Fail"),
  SectionType("ES","0200","SectionTypeError"),
  USER_USED("U", "0003", "UserUsed"),
  USER_NOT_LOGGIN("E", "0002", "User not loggin"),
  NOT_FOUND_IDENTITY_NO("E", "0003", "Identity no not found"),
  UNAUTHORIZED("E", "0004", "Unauthorized exception"),
  S_WS(
    
    "S", "S-WS-00-0000", "Success"),
  E_WS("E", "E-WS-00-0000", "Error"),
  W_WS("W", "W-WS-00-0000", "Warning"),
  E_WS_DETAIL("E", "E-WS-00-0001", "Contains Error"),
  W_WS_DETAIL("W", "W-WS-00-0001", "Contains Warning"),
  E_WS_SCORE_MISSING_INFO(
    "E", "E-WS-01-0001", "Missing Data"),
  E_WS_SCORE_NOT_FOUND_STAKEHOLDER("E", "E-WS-01-0002", "Stakeholder Not Found!"),
  E_WS_SCORE_NOT_FOUND_DATA("E", "E-WS-01-0003", "Data Not Found!"),
  W_WS_SCORE_NOT_CALCULATE_CHARPOINT("W", "W-WS-01-0001", "CharacterPoint cannot calculate!"),
  W_WS_SCORE_NOT_CALCULATE_WORKINGMETHOD("W", "W-WS-01-0001", "Working method cannot calculate!"),
  E_WS_ADDITIONAL_DATA_MISSING_INFO(
    "E", "E-WS-02-0001", "Missing Data"),
  E_WS_ADDITIONAL_DATA_NOT_FOUND_STAKEHOLDER("E", "E-WS-02-0002", "Stakeholder Not Found!"),
  I_WS_ADDITIONAL_DATA_ADDED_ITEM("I", "I-WS-02-0001", "Item has been update!"),
  W_WS_ADDITIONAL_DATA_WRONG_KEY("W", "W-WS-02-0001", "Wrong key for additional data!");
  
  private String type;
  
  private String code;
  
  private String message;
  
  ErrorType(String type, String code, String message) {
    this.type = type;
    this.code = code;
    this.message = message;
  }
  
  public String getCode() {
    return this.code;
  }
  
  public String getMessage() {
    return this.message;
  }
  
  public String getType() {
    return this.type;
  }
}


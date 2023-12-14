package com.springMvcWebService.dao;

import java.util.List;

import com.springMvcWebService.beans.Entity;
import com.springMvcWebService.beans.PdfData;
import com.springMvcWebService.beans.Section;
import com.springMvcWebService.beans.SectionData;
import com.springMvcWebService.beans.User;
import com.springMvcWebService.dto.Token;
import com.springMvcWebService.dto.UserData;


public interface PlumbierDAO {

	  User plumbierfindUser(User paramUser);
	  
	  User plumbierSingUp(User paramUser);
	  
	  List<User> getAllUserNotBoss();
	  
	  Boolean plumbierDelete(long paramLong);
	  
	  List<Entity> plumbierFindAll();
	  
	  SectionData plumbierFindById(long paramLong);
	  
	  User findByLoginUser(User paramUser);

	  User findById(Long id);
	  
	  List<SectionData> tblplumbierFindAll();
	  
	  SectionData plumbiersave(SectionData sectionData);
	  
	  UserData plumbierFindAllUserData(long id);

	  PdfData plumbierPDFSave(PdfData data);
	  
	  PdfData downloadFindById(long uid, long id);
	  
	  Section sectionSave(Section section);

	  List<Section> getSection();

	UserData isUidPlumbierData(long uid);

	List<SectionData> findBySid();

	List<PdfData> findAllPDF();

	Boolean UploadProfileImg(User user);

	String requestSaveTable(String user);

//	UserWork worksAdd(UserWork work);

	List<PdfData> findByUidPDF(long uid);


	User findByUserInformation(String userName);

	Boolean userToken(Token token);

	Token findByTokenUser(String user);

	Token findByToken(String token);

	Boolean findUpdateToken(Token token);

	Boolean userPasswordUpdate(String user, String pass);

	Section findSection(int sectionType);
	
	User findByUserBoss(long uid);
	
	Boolean deletePdf(long uid, long workId);

	Boolean deleteUser(long id);

	Boolean deleteUserPdf(long uid);

	Boolean plumbierUserData(long uid);




}

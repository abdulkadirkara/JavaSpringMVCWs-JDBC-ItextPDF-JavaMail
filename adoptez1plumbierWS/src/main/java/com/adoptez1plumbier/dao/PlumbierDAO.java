package com.adoptez1plumbier.dao;

import java.util.List;

import com.adoptez1plumbier.beans.Entity;
import com.adoptez1plumbier.beans.PdfData;
import com.adoptez1plumbier.beans.Section;
import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dto.Token;
import com.adoptez1plumbier.dto.UserData;


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

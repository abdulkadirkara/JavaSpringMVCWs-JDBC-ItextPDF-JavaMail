package com.adoptez1plumbier.dao;

import java.util.List;

import com.adoptez1plumbier.beans.Entity;
import com.adoptez1plumbier.beans.PdfData;
import com.adoptez1plumbier.beans.Section;
import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dto.UserData;


public interface PlumbierDAO {

	  User plumbierfindUser(User paramUser);
	  
	  User plumbierSingUp(User paramUser);
	  
	  List<User> getAllUserNotBoss();
	  
//	  WorkPlace plumbierSaveWorkPlace(WorkPlace paramWorkPlace);
	  
//	  SectionData plumbierPalace(SectionData paramSectionData);
//	  
//	  SectionData plumbiersaveWorkprop(SectionData paramSectionData);
//	  
//	  SectionData plumbiersaveEvalution(SectionData paramSectionData);
	  
	  Boolean plumbierDelete(long paramLong);
	  
	  List<Entity> plumbierFindAll();
	  
	  SectionData plumbierFindById(long paramLong);
	  
	  User findByLoginUser(User paramUser);

	  User findById(Long id);
	  
	  List<SectionData> tblplumbierFindAll();
	  
	  SectionData plumbiersave(SectionData sectionData);
	  
	  UserData plumbierFindAllUserData(long id);

	  PdfData plumbierPDFSave(PdfData data);
	  
	  PdfData downloadFindById(Long id);
	  
	  Section sectionSave(Section section);

	  List<Section> getSection();

	List<SectionData> isPlumbierData();

	List<SectionData> findBySid();

}

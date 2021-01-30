package com.adoptez1plumbier.dao;

import com.adoptez1plumbier.beans.Entity;
import com.adoptez1plumbier.beans.PdfData;
import com.adoptez1plumbier.beans.Section;
import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dto.UserData;
import com.adoptez1plumbier.mapper.FileRowMapper;
import com.adoptez1plumbier.mapper.PlumbierRowMapper;
import com.adoptez1plumbier.mapper.SectionRowMapper;
import com.adoptez1plumbier.mapper.UserDataExtractor;
import com.adoptez1plumbier.mapper.UserRowMapper;
import com.adoptez1plumbier.util.DBUtil;
import com.adoptez1plumbier.util.Preferences;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PlumbierDaoImpl implements PlumbierDAO {
	
  @Autowired
  JdbcTemplate jdbcTemplate;
  
//@Override
//  public SectionData plumbierPalace(SectionData sectionData) {
//    sectionData.setId(DBUtil.nextLongId( jdbcTemplate, "palace", "ID"));
//    String sql = "INSERT INTO palace (ID,UID, DATE, FEILDNAME, FEILDINPUT) VALUES (?, ?, ?, ?, ?)";
//     jdbcTemplate.update(sql, new Object[] { Long.valueOf(sectionData.getId()),sectionData.getUserId().getId(), new Date(), sectionData.getFieldName(), sectionData.getFieldInput() });
//    return sectionData;
//  }
//
//  @Override
//  public SectionData plumbiersaveWorkprop(SectionData workprop) {
//    workprop.setId(DBUtil.nextLongId( jdbcTemplate, "WorkProp", "ID"));
//    String sql = "INSERT INTO WorkProp (ID, UID, FEILDNAME, FEILDNUMBERFIRSTSECTION, FEILDISNUMBERSECONDSECTION, FEILDSIMAGESECONDSECTION) VALUES (?, ?, ?, ?, ?, ?)";
//     jdbcTemplate.update(sql, new Object[] { Long.valueOf(workprop.getId()),workprop.getUserId().getId(), workprop.getFieldName(), workprop.getFieldNumberFirstSection(), Boolean.valueOf(workprop.getIsFieldIsNumberSecondSection()), workprop.getFieldsImageSecondSection() });
//    return workprop;
//  }
//  @Override
//  public SectionData plumbiersaveEvalution(SectionData evalution) {
//    evalution.setId(DBUtil.nextLongId( jdbcTemplate, "evaluationTable", "ID"));
//    String sql = "INSERT INTO evaluationTable (ID, UID,FEILDNAME, FEILDFEELING) VALUES (?, ?, ?, ?)";
//     jdbcTemplate.update(sql, new Object[] { Long.valueOf(evalution.getId()), evalution.getUserId().getId(), evalution.getFieldName(), Integer.valueOf(evalution.getFieldsFeeling())});
//    return evalution;
//  }
  
  @Override
  public SectionData plumbiersave(SectionData sectionData) {
    sectionData.setId(DBUtil.nextLongId( jdbcTemplate, "tbl_plumbier", "ID"));
    String sql = "INSERT INTO tbl_plumbier (ID, UID,SID,SECTIONID,FIELDNAME,FIELDINPUT,FIELDNUMBERFIRSTSECTION,FIELDISNUMBERSECONDSECTION,FIELDSIMAGESECONDSECTION,FIELDFEELING) VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
     jdbcTemplate.update(sql, new Object[] {sectionData.getId(),sectionData.getUserId().getId(),sectionData.getSid(),sectionData.getSectionId(), 
    		 sectionData.getFieldName(),sectionData.getFieldInput(),sectionData.getFieldNumberFirstSection(),
    		 Boolean.valueOf(sectionData.getIsFieldIsNumberSecondSection()),sectionData.getFieldsImageSecondSection(), Integer.valueOf(sectionData.getFieldsFeeling())});
    return sectionData;
  }
  
  @Override
  public Boolean plumbierDelete(long id) {
    String sql = "DELETE FROM quitus_travaux WHERE ID = ?";
    int result = jdbcTemplate.update(sql, new Object[] { Long.valueOf(id) });
    return Boolean.valueOf((result > 0));
  }
//  @Override
//  public List<Entity> plumbierFindAll() {
//    String sql = "SELECT P.ID ID,P.UID UID,P.FEILDNAME FIELDNAME,P.FEILDINPUT FIELDINPUT,P.DATE DATE,W.FEILDNAME FIELDNAME,W.FEILDNUMBERFIRSTSECTION FIELDNUMBERFIRSTSECTION,W.FEILDISNUMBERSECONDSECTION FIELDISNUMBERSECONDSECTION,"
//    		+ "W.FEILDSIMAGESECONDSECTION FIELDSIMAGESECONDSECTION,E.FEILDNAME FIELDNAME,E.FEILDFEELING FIELDFEELING,D.PDF_DATA PDFDATA FROM palace P INNER JOIN WorkProp W ON P.ID = W.ID INNER JOIN evaluationTable E "
//    		+ "ON P.ID = E.ID INNER JOIN pdfData D  ON P.ID = D.ID  ORDER BY P.ID";
//    List<Entity> entities = jdbcTemplate.query(sql, new PlumbierRowMapper());
//    return entities; 
//  }
  @Override
  public List<SectionData> tblplumbierFindAll() {
    String sql = "Select * from tbl_plumbier";
    List<SectionData> entities = jdbcTemplate.query(sql, new PlumbierRowMapper());
    return entities; 
  }
  
  @Override
  public SectionData plumbierFindById(long id) {
    String sql = "SELECT * FROM tbl_plumbier WHERE ID = ?";
    List<SectionData> entities = jdbcTemplate.query(sql, new Object[] { Long.valueOf(id) }, new PlumbierRowMapper());
    if (entities.isEmpty())
      return null; 
    return entities.get(0);
  }
  @Override
  public User plumbierSingUp(User user) {
    user.setId(DBUtil.nextLongId(jdbcTemplate, "tbl_login", "ID"));
    String sql = "INSERT INTO tbl_login (ID,NAME,PHONE,P_USERNAME,P_PASSWORD,PROFILEIMG,isADMIN) VALUES (?,?,?,?,?,?,?)";
    jdbcTemplate.update(sql, new Object[] { Long.valueOf(user.getId()), user.getName(), user.getPhone(), 
          user.getUsername(), user.getPassword(),user.getProfileImg(), Integer.valueOf(user.getIsAdmin()) });
    return user;
  }
  @Override
  public User plumbierfindUser(User user) {
    user.setId(DBUtil.nextLongId( jdbcTemplate, "tbl_login", "ID"));
    String sql = "select * from tbl_login WHERE P_USERNAME = ? and P_PASSWORD=? ";
    List<User> entities =  jdbcTemplate.query(sql, new Object[] { user.getUsername(), user.getPassword() }, new UserRowMapper());
    if (entities.isEmpty())
      return null; 
    return entities.get(0);
  }
  @Override
  public User findByLoginUser(User user) {
    user.setId(DBUtil.nextLongId( jdbcTemplate, "tbl_login", "ID"));
    String sql = "select * from tbl_login WHERE P_USERNAME = ?";
    List<User> entities =  jdbcTemplate.query(sql, new Object[] { user.getUsername() }, new UserRowMapper());
    if (entities.isEmpty())
      return null; 
    return entities.get(0);
  }
  @Override
  public List<User> getAllUserNotBoss() {
    String sql = "select * from tbl_login WHERE isADMIN=0";
    List<User> entities =  jdbcTemplate.query(sql, new UserRowMapper());
    if (entities.isEmpty())
      return null; 
    return entities;
  }
  
	@Override
	public User findById(Long id) {
		String sql = "SELECT * FROM tbl_login WHERE ID = ?";
		List<User> users = jdbcTemplate.query(sql, new Object[]{id}, new UserRowMapper());
		 if (id==null)
		      return null; 
		return users.get(0);
		
	}
	
	@Override
	  public PdfData plumbierPDFSave(PdfData data) {
	    data.setId(DBUtil.nextLongId(jdbcTemplate, "pdfData", "ID"));
	    String sql = "INSERT INTO pdfData (ID,UID,NAME,DATE,PDF_DATA) VALUES (?,?,?,?,?)";
	    jdbcTemplate.update(sql, new Object[] { Long.valueOf(data.getId()), data.getUid().getId(),data.getName(), new Date(),data.getPdf()});
	    return data;
	  }
	
	@Override
	public PdfData downloadFindById(Long id) {
		String sql = "SELECT * FROM pdfData WHERE ID=?";
		List<PdfData> entities = jdbcTemplate.query(sql, new Object[]{id}, new FileRowMapper());
		if(Preferences.isEmpty(entities)) {
			return null;
		} else {
			return entities.get(0);
		}
	}

	@Override
	public Section sectionSave(Section section) {
		section.setId(DBUtil.nextLongId(jdbcTemplate, "Section", "ID"));
	    String sql = "INSERT INTO Section (ID,SECTIONNAME,SECTIONTYPE) VALUES (?,?,?)";
	    jdbcTemplate.update(sql,section.getId(), section.getSectionName(),section.getSectionType());
	    return section;
	}
	
	@Override
	public List<Section> getSection() {
	    String sql = "SELECT * FROM Section";
	    List<Section> entities =  jdbcTemplate.query(sql, new SectionRowMapper());
	    if (entities.isEmpty())
	        return null; 
	      return entities;
	}
	
//	@Override
//	  public List<SectionData> isPlumbierData() {
//	    String sql = "Select FROM tbl_plumbier";
//	    List<SectionData> entities = jdbcTemplate.query(sql, new SectionDataRowMapper());
//	    if (entities.isEmpty())
//	        return null; 
//	      return entities;
//	}
	
	@Override
	 public UserData plumbierFindAllUserData(long uid) {
	    String sql = "SELECT P.ID ID, P.UID UID, U.NAME NAME, U.P_USERNAME USERNAME, U.PHONE PHONE, U.PROFILEIMG IMG, P.DATE DATE, P.FIELDNAME FIELDNAME, P.FIELDINPUT FIELDINPUT, "
	    		+ "P.FIELDNUMBERFIRSTSECTION FIELDNUMBERFIRSTSECTION,P.FIELDISNUMBERSECONDSECTION FIELDISNUMBERSECONDSECTION,"
	    		+ " P.FIELDSIMAGESECONDSECTION FIELDSIMAGESECONDSECTION,P.FIELDFEELING FIELDFEELING,D.PDF_DATA PDFDATA,D.ID PDF_ID,"
	    		+ "D.NAME NAME FROM tbl_plumbier P INNER JOIN pdfData D  ON P.SID = D.ID INNER JOIN tbl_login U ON P.UID = U.ID"
	    		+ " where P.UID=? ORDER BY P.ID ";
	    
		UserData entities = jdbcTemplate.query(sql, new Object[] { uid }, new UserDataExtractor());
		return entities;
	  }

	@Override
	public List<Entity> plumbierFindAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SectionData> isPlumbierData() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<SectionData> findBySid() {
		String sql = "SELECT * FROM tbl_plumbier";
		List<SectionData> entities = jdbcTemplate.query(sql, new PlumbierRowMapper());
		 if (entities.isEmpty())
		        return null; 
		      return entities;
		
	}

}

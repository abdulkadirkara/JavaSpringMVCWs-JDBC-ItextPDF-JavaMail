package com.adoptez1plumbier.dao;

import com.adoptez1plumbier.beans.Entity;
import com.adoptez1plumbier.beans.PdfData;
import com.adoptez1plumbier.beans.Section;
import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dto.Token;
import com.adoptez1plumbier.dto.UserData;
import com.adoptez1plumbier.mapper.FileRowMapper;
import com.adoptez1plumbier.mapper.FindUserRowMapper;
import com.adoptez1plumbier.mapper.PlumbierRowMapper;
import com.adoptez1plumbier.mapper.SectionRowMapper;
import com.adoptez1plumbier.mapper.TokenRowMapper;
import com.adoptez1plumbier.mapper.UserDataExtractor;
import com.adoptez1plumbier.mapper.UserRowMapper;
import com.adoptez1plumbier.mapper.UserTokenRowMapper;
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
		sectionData.setId(DBUtil.nextLongId(jdbcTemplate, "tbl_plumbier", "ID"));
		String sql = "INSERT INTO tbl_plumbier (ID, UID,SID,DATE,SECTIONID,FIELDNAME,FIELDINPUT,FIELDNUMBERFIRSTSECTION,FIELDISNUMBERSECONDSECTION,FIELDSIMAGESECONDSECTION,FIELDFEELING) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql,
				new Object[] { sectionData.getId(), sectionData.getUserId().getId(), sectionData.getSid(), new Date(),
						sectionData.getSectionId(), sectionData.getFieldName(), sectionData.getFieldInput(),
						sectionData.getFieldNumberFirstSection(), sectionData.getIsFieldIsNumberSecondSection(),
						sectionData.getFieldsImageSecondSection(), Integer.valueOf(sectionData.getFieldsFeeling()) });
		return sectionData;
	}

	@Override
	public Boolean plumbierDelete(long sid) {
		String sql = "DELETE FROM tbl_plumbier WHERE SID = ?";
		int result = jdbcTemplate.update(sql, new Object[] { Long.valueOf(sid) });
		return Boolean.valueOf((result > 0));
	}
	
	@Override
	public Boolean plumbierUserData(long uid) {
		String sql = "DELETE FROM tbl_plumbier WHERE UID = ?";
		int result = jdbcTemplate.update(sql, new Object[] { Long.valueOf(uid) });
		return Boolean.valueOf((result > 0));
	}
	
	@Override
	public List<SectionData> tblplumbierFindAll() {
		String sql = "Select * from tbl_plumbier";
		List<SectionData> entities = jdbcTemplate.query(sql, new PlumbierRowMapper());
		return entities;
	}

	@Override
	public SectionData plumbierFindById(long id) {
		String sql = "SELECT * FROM tbl_plumbier WHERE ID = ?";
		List<SectionData> entities = jdbcTemplate.query(sql, new Object[] { Long.valueOf(id) },
				new PlumbierRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
	}

	@Override
	public Boolean UploadProfileImg(User user) {
		String sql = "update tbl_login set PROFILEIMG=? where id=?";
		int result = jdbcTemplate.update(sql, user.getProfileImg(), user.getId());
		return Boolean.valueOf((result > 0));
	}

	@Override
	public String requestSaveTable(String data) {
		long id = DBUtil.nextLongId(jdbcTemplate, "tbl_requestData", "ID");
		String sql = "INSERT INTO tbl_requestData (ID,DATE,JSONDATA) VALUES (?,?,?)";
		jdbcTemplate.update(sql, new Object[] { id, new Date(), data });
		return data;
	}

	@Override
	public User plumbierSingUp(User user) {
		user.setId(DBUtil.nextLongId(jdbcTemplate, "tbl_login", "ID"));
		String sql = "INSERT INTO tbl_login (ID,NAME,PHONE,P_USERNAME,P_PASSWORD,PROFILEIMG,isADMIN) VALUES (?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { Long.valueOf(user.getId()), user.getName(), user.getPhone(),
				user.getUsername(), user.getPassword(), user.getProfileImg(), Integer.valueOf(user.getIsAdmin()) });
		return user;
	}

	@Override
	public User plumbierfindUser(User user) {
		user.setId(DBUtil.nextLongId(jdbcTemplate, "tbl_login", "ID"));
		String sql = "select * from tbl_login WHERE P_USERNAME = ? and P_PASSWORD=? ";
		List<User> entities = jdbcTemplate.query(sql, new Object[] { user.getUsername(), user.getPassword() },
				new UserRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
	}

	@Override
	public User findByLoginUser(User user) {
		user.setId(DBUtil.nextLongId(jdbcTemplate, "tbl_login", "ID"));
		String sql = "select * from tbl_login WHERE P_USERNAME = ?";
		List<User> entities = jdbcTemplate.query(sql, new Object[] { user.getUsername() }, new UserRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
	}

	@Override
	public User findByUserInformation(String userName) {
		String sql = "select * from tbl_login WHERE P_USERNAME = ?";
		List<User> entities = jdbcTemplate.query(sql, new Object[] { userName }, new UserRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
	}
	
	@Override
	public User findByUserBoss(long uid) {
		String sql = "select * from tbl_login WHERE ID = ?";
		List<User> entities = jdbcTemplate.query(sql, new Object[] { uid }, new UserRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
	}

	@Override
	public Boolean userToken(Token token) {
		long id=DBUtil.nextLongId(jdbcTemplate, "token", "ID");
		String sql = "INSERT INTO token (ID, USER, TOKEN) VALUES(?,?,?)";
		int result = jdbcTemplate.update(sql, new Object[] { Long.valueOf(id),token.getUser().getUsername(),token.getToken()});
		return Boolean.valueOf((result > 0));
	}
	
	@Override
	public Token findByTokenUser(String user) {
		String sql = "SELECT  * from tbl_login l  inner join token t on t.user=l.P_USERNAME where l.P_USERNAME=?";
		List<Token> entities = jdbcTemplate.query(sql, new Object[] { user }, new UserTokenRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
	}
	
	@Override
	public Token findByToken(String token) {
		String sql = "Select * from token where token=?";
		List<Token> entities = jdbcTemplate.query(sql, new Object[] {token},new TokenRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
	}
	
	@Override
	public Boolean findUpdateToken(Token token) {
		String sql = "Update token set TOKEN=? where USER=?";
		int result = jdbcTemplate.update(sql, new Object[] {token.getToken(), token.getUser().getUsername()});
		return Boolean.valueOf((result > 0));
	}
	
	@Override
	public List<User> getAllUserNotBoss() {
		String sql = "select * from tbl_login WHERE isADMIN=0";
		List<User> entities = jdbcTemplate.query(sql, new UserRowMapper());
		if (entities.isEmpty())
			return null;
		return entities;
	}
	
	@Override
	public User findById(Long id) {
		String sql = "SELECT * FROM tbl_login WHERE ID = ?";
		List<User> users = jdbcTemplate.query(sql, new Object[] { id }, new FindUserRowMapper());
		if (id == null)
			return null;
		return users.get(0);
	}
	
	@Override
	public Boolean deleteUser(long id) {
		String sql = "DELETE FROM tbl_login WHERE ID = ?";
		int result = jdbcTemplate.update(sql, new Object[] { Long.valueOf(id) });
		return Boolean.valueOf((result > 0));
	}


	@Override
	public Boolean userPasswordUpdate(String user, String pass) {
		String sql = "UPDATE tbl_login set P_PASSWORD=? WHERE P_USERNAME = ?";
		int result = jdbcTemplate.update(sql, new Object[] { pass, user });
		return Boolean.valueOf((result > 0));
	}

	@Override
	public PdfData plumbierPDFSave(PdfData data) {
		data.setId(DBUtil.nextLongId(jdbcTemplate, "pdfData", "ID"));
		String sql = "INSERT INTO pdfData (ID,UID,UUID,WORKID,NAME,DATE,PDF_DATA,SIGNATUREDATA) VALUES (?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] { Long.valueOf(data.getId()), data.getUid().getId(), data.getPdfid(),
				data.getWorkId(), data.getName(), new Date(), data.getPdf(),data.getSignature()});
		return data;
	}

	@Override
	public PdfData downloadFindById(long uid, long id) {
		String sql = "SELECT * FROM pdfData WHERE UID=? and WORKID=? ";
		List<PdfData> entities = jdbcTemplate.query(sql, new Object[] { uid, id }, new FileRowMapper());
		if (Preferences.isEmpty(entities)) {
			return null;
		} else {
			return entities.get(0);
		}
	}

	@Override
	public List<PdfData> findAllPDF() {
		String sql = "SELECT * FROM pdfData";
		List<PdfData> entities = jdbcTemplate.query(sql, new FileRowMapper());
		if (entities.isEmpty())
			return null;
		return entities;
	}

	@Override
	public List<PdfData> findByUidPDF(long uid) {
		String sql = "SELECT * FROM pdfData where UID=? order by UID asc";
		List<PdfData> entities = jdbcTemplate.query(sql, new Object[] { uid }, new FileRowMapper());
		if (entities.isEmpty())
			return null;
		return entities;
	}
	
	@Override
	public Boolean deletePdf(long id, long workId) {
		String sql = "DELETE FROM pdfData WHERE UID =? and WORKID=?";
		int result = jdbcTemplate.update(sql, new Object[] { Long.valueOf(id), Long.valueOf(workId) });
		return Boolean.valueOf((result > 0));
	}
	
	@Override
	public Boolean deleteUserPdf(long uid) {
		String sql = "DELETE FROM pdfData WHERE UID =?";
		int result = jdbcTemplate.update(sql, new Object[] { Long.valueOf(uid)});
		return Boolean.valueOf((result > 0));
	}
	

	@Override
	public Section sectionSave(Section section) {
		section.setId(DBUtil.nextLongId(jdbcTemplate, "Section", "ID"));
		String sql = "INSERT INTO Section (ID,SECTIONNAME,SECTIONTYPE) VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, section.getId(), section.getSectionName(), section.getSectionType());
		return section;
	}

	@Override
	public List<Section> getSection() {
		String sql = "SELECT * FROM Section";
		List<Section> entities = jdbcTemplate.query(sql, new SectionRowMapper());
		if (entities.isEmpty())
			return null;
		return entities;
	}
	
	@Override
	public Section findSection(int sectionType ) {
		String sql = "SELECT * FROM Section where SECTIONTYPE=? ";
		List<Section> entities = jdbcTemplate.query(sql,new Object[] { sectionType }, new SectionRowMapper());
		if (entities.isEmpty())
			return null;
		return entities.get(0);
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
		String sql = "SELECT P.ID ID, P.UID UID, P.SID SID, P.DATE DATE, U.ID U_ID, U.NAME NAME, U.P_USERNAME USERNAME, U.PHONE PHONE, U.PROFILEIMG IMG,"
				+ "P.DATE DATE, P.FIELDNAME FIELDNAME, P.FIELDINPUT FIELDINPUT, P.FIELDNUMBERFIRSTSECTION FIELDNUMBERFIRSTSECTION,"
				+ "P.FIELDISNUMBERSECONDSECTION FIELDISNUMBERSECONDSECTION, P.FIELDSIMAGESECONDSECTION FIELDSIMAGESECONDSECTION,"
				+ "P.FIELDFEELING FIELDFEELING FROM tbl_plumbier P RIGHT JOIN tbl_login U ON P.UID = U.ID where U.ID=? ORDER BY P.ID";
		UserData entities = jdbcTemplate.query(sql, new Object[] { uid }, new UserDataExtractor());
		return entities;
	}

	@Override
	public List<Entity> plumbierFindAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserData isUidPlumbierData(long uid) {
		String sql = " SELECT DISTINCT SID, F.WORKID WORKID, F.UID UID, F.UUID UUID, P.DATE DATE, U.NAME NAME, U.P_USERNAME USERNAME,\r\n"
				+ " U.PHONE PHONE, U.PROFILEIMG IMG, F.PDF_DATA PDF_DATA FROM tbl_plumbier P RIGHT JOIN tbl_login U ON  U.ID=P.UID\r\n"
				+ " LEFT JOIN pdfData F ON P.SID=F.ID  where U.ID =? ORDER BY P.UID";
		UserData entities = jdbcTemplate.query(sql, new Object[] { uid }, new UserDataExtractor());
		return entities;

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

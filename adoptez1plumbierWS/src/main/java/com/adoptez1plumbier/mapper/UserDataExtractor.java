package com.adoptez1plumbier.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.adoptez1plumbier.beans.PdfData;
import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dto.UserData;

public class UserDataExtractor  implements ResultSetExtractor<UserData>{

	@Override
	public UserData extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<SectionData>works=new ArrayList<SectionData>();
		SectionData sectionData= null;
		long id=0;
		UserData userData=new UserData();
		while (rs.next()) {
			User user=new User();
			user.setId(rs.getLong("UID"));
			user.setName(rs.getString("NAME"));
			user.setUsername(rs.getString("USERNAME"));
			user.setPhone(rs.getString("PHONE"));
			user.setProfileImg(rs.getString("IMG"));
			userData.setUserInformation(user);
				sectionData=new SectionData();
				sectionData.setId(rs.getLong("ID"));
				sectionData.setDate(rs.getString("DATE"));
				sectionData.setFieldName(rs.getString("FIELDNAME"));
				sectionData.setFieldInput(rs.getString("FIELDINPUT"));
				sectionData.setDate(rs.getString("DATE"));
				sectionData.setFieldName(rs.getString("FIELDNAME"));
				sectionData.setFieldNumberFirstSection(rs.getInt("FIELDNUMBERFIRSTSECTION"));
				sectionData.setFieldIsNumberSecondSection(rs.getBoolean("FIELDISNUMBERSECONDSECTION"));
				sectionData.setFieldsImageSecondSection(rs.getString("FIELDSIMAGESECONDSECTION"));
				sectionData.setFieldsFeeling(rs.getInt("FIELDFEELING"));
				sectionData.setPdfId(rs.getLong("PDF_ID"));
				works.add(sectionData);
			
		}
		userData.setUid(id);
		userData.setUserWorks(works);
		return userData;
	}

}

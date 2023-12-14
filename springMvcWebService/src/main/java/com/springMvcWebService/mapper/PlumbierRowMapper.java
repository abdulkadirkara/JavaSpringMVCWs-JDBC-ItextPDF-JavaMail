package com.springMvcWebService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springMvcWebService.beans.SectionData;
import com.springMvcWebService.beans.User;


public class PlumbierRowMapper implements RowMapper<SectionData>{

	public SectionData mapRow(ResultSet rs, int rowNum) throws SQLException {

		Long id=rs.getLong("ID");
		
		SectionData entity=new SectionData();
		entity.setId(id);
		User user=new User();
		user.setId(rs.getLong("UID"));
		entity.setSid(rs.getLong("SID"));
		entity.setUserId(user);
		entity.setDate(rs.getString("DATE"));
		entity.setFieldName(rs.getString("FIELDNAME"));
		entity.setFieldInput(rs.getString("FIELDINPUT"));
		entity.setFieldNumberFirstSection(rs.getInt("FIELDNUMBERFIRSTSECTION"));
		entity.setFieldIsNumberSecondSection(rs.getBoolean("FIELDISNUMBERSECONDSECTION"));
		entity.setFieldsImageSecondSection(rs.getString("FIELDSIMAGESECONDSECTION"));
		entity.setFieldsFeeling(rs.getInt("FIELDFEELING"));

		return entity;
		
	}
}

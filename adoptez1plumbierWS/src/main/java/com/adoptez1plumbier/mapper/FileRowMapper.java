package com.adoptez1plumbier.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.adoptez1plumbier.beans.PdfData;
import com.adoptez1plumbier.beans.User;

public class FileRowMapper implements  RowMapper<PdfData> {

	@Override
	public PdfData mapRow(ResultSet rs, int rowNum) throws SQLException  {
		PdfData data=new PdfData();
		data.setId(rs.getLong("ID"));
		User user = new User();
		user.setId(rs.getLong("UID"));
		data.setUid(user);
		data.setDate(rs.getDate("DATE"));
		data.setName(rs.getString("NAME"));
		data.setPdf(rs.getBytes("PDF_DATA"));
		
		return data;
		
		

	}


}

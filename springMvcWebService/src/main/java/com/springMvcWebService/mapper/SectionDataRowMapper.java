package com.springMvcWebService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.springMvcWebService.beans.SectionData;

public class SectionDataRowMapper  implements RowMapper<SectionData>{


	@Override
	public SectionData mapRow(ResultSet rs, int rowNum) throws SQLException {
		SectionData sectionData = new SectionData();
		sectionData.setId(rs.getLong("ID"));
		sectionData.setFieldName(rs.getString("FIELDNAME"));
		sectionData.setFieldInput(rs.getString("FIELDINPUT"));
		sectionData.setFieldNumberFirstSection(rs.getInt("FIELDNUMBERFIRSTSECTION"));
		sectionData.setFieldIsNumberSecondSection(rs.getBoolean("FIELDISNUMBERSECONDSECTION"));
		sectionData.setFieldsImageSecondSection(rs.getString("FIELDSIMAGESECONDSECTION"));
		sectionData.setFieldsFeeling(rs.getInt("FIELDFEELING"));
		return sectionData;
		
	}

}

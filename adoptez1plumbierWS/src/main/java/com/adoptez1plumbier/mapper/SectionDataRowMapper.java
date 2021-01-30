package com.adoptez1plumbier.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.adoptez1plumbier.beans.SectionData;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dto.Feeling;
import com.adoptez1plumbier.dto.Palace;
import com.adoptez1plumbier.dto.UserData;
import com.adoptez1plumbier.dto.WorkProp;

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

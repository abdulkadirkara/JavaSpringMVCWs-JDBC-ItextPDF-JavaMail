package com.adoptez1plumbier.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.adoptez1plumbier.beans.Section;

public class SectionRowMapper implements  RowMapper<Section> {

	@Override
	public Section mapRow(ResultSet rs, int rowNum) throws SQLException  {
		Section section=new Section();
		section.setId(rs.getLong("ID"));
		section.setSectionName(rs.getString("SECTIONNAME"));
		section.setSectionType(rs.getInt("SECTIONTYPE"));
	
		return section;
	}
}

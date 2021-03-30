package com.adoptez1plumbier.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.adoptez1plumbier.beans.User;
import com.adoptez1plumbier.dto.UserData;
import com.adoptez1plumbier.dto.UserWork;

public class UserDataExtractor implements ResultSetExtractor<UserData> {

	@Override
	public UserData extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<UserWork> works = new ArrayList<UserWork>();
		UserData userData = new UserData();
	while (rs.next()) {
		Long uid = rs.getLong("UID");
			User user = new User();
			user.setId(uid);
			user.setName(rs.getString("NAME"));
			user.setUsername(rs.getString("USERNAME"));
			user.setPhone(rs.getString("PHONE"));
			user.setProfileImg(rs.getString("IMG"));
			userData.setUserInformation(user);
			if (uid >0) {
				UserWork userwork = new UserWork();
				userwork.setId(rs.getLong("WORKID"));
				userwork.setDate(rs.getString("DATE"));
				userwork.setName("Quitus "+rs.getString("WORKID"));
				works.add(userwork);
			}
			
			userData.setUid(uid);
			userData.setUserWorks(works);
		}
		
		return userData;
	}

}

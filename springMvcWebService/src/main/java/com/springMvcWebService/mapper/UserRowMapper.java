package com.springMvcWebService.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.springMvcWebService.beans.User;

public class UserRowMapper  implements RowMapper<User>{


	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user=new User();
		user.setId(rs.getLong("ID"));
		user.setName(rs.getString("NAME"));
		user.setPhone(rs.getString("PHONE"));
		user.setUsername(rs.getString("P_USERNAME"));
		user.setPassword(rs.getString("P_PASSWORD"));
		user.setProfileImg(rs.getString("PROFILEIMG"));
		user.setIsAdmin(rs.getInt("isADMIN"));
		return user;
	}

}

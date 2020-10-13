package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Cusermapper implements RowMapper<Cuser>{

	@Override
	public Cuser mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cuser user = new Cuser();
		user.setUsername(rs.getString("username"));
		user.setPswd(rs.getString("pswd"));
		user.setEmail(rs.getString("email"));
		return user;
	}

}
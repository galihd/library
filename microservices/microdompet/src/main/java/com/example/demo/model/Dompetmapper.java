package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Dompetmapper implements RowMapper<Dompet>{
	

	@Override
	public Dompet mapRow(ResultSet rs, int rowNum) throws SQLException {
		Dompet dompet = new Dompet();
		dompet.setUsername(rs.getString("username"));
		dompet.setSaldo(rs.getInt("saldo"));
		dompet.setDompetid(rs.getString("dompetid"));
		return dompet;
	}
	
}

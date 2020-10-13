package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TransaksiMapper implements RowMapper<Transaksi>{

	@Override
	public Transaksi mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transaksi transaksi = new Transaksi();
		transaksi.setTransid(rs.getString("transid"));
		transaksi.setDompetid(rs.getString("dompetid"));
		transaksi.setJenis(rs.getString("jenis"));
		transaksi.setTotal(rs.getInt("total"));
		transaksi.setTanggal(rs.getDate("tanggal"));
		return transaksi;
	}

}

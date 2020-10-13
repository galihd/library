package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Daftarpinjamanmapper implements RowMapper<Daftarpinjaman>{

	@Override
	public Daftarpinjaman mapRow(ResultSet rs, int rowNum) throws SQLException {
		Daftarpinjaman daftar = new Daftarpinjaman();
		daftar.setJudulbuku(rs.getString("judul buku"));
		daftar.setLamapinjam(rs.getInt("lama pinjam"));
		daftar.setTanggalpinjam(rs.getDate("tanggal pinjam"));
		daftar.setTransid(rs.getString("no transaksi"));
		return daftar;
	}
	

}

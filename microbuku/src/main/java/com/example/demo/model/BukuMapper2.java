package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BukuMapper2 implements RowMapper<Buku>{

	@Override
	public Buku mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Buku buku = new Buku();
		buku.setBukuid(rs.getString("bukuid"));
		buku.setJudul(rs.getString("judul"));
		buku.setTahun(rs.getString("tahunterbit"));
		buku.setGenre(rs.getString("genre"));
		buku.setHarga(rs.getInt("harga"));
		buku.setTransid(rs.getString("transid"));
		buku.setTanggalupdate(rs.getDate("tanggalupdate"));
		return buku;
	}

}
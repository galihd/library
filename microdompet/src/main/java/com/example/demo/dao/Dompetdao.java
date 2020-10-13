package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Daftarpinjaman;
import com.example.demo.model.Daftarpinjamanmapper;
import com.example.demo.model.Daftarpinjamanuser;
import com.example.demo.model.Dompet;
import com.example.demo.model.Dompetmapper;

@Repository("dompetdb")
public class Dompetdao implements DompetdaoInt{
	@Bean
	static DataSource getDatasource() {
		return DataSourceBuilder.create()
				.url("jdbc:oracle:thin:@//localhost:1521/XEPDB1")
				.username("microdompet")
				.password("mstest")
				.build();
	}
	
	@Autowired
	static JdbcTemplate jdbctemplateobj = new JdbcTemplate(getDatasource());

	@Override
	public ResponseEntity<Object> tambahsaldo(String dompetid, int jumlah) {
		
		jdbctemplateobj.update("update dompet set saldo = saldo + ? where dompetid = ?"
				,jumlah,dompetid);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> kurangsaldo(String dompetid, int jumlah) {
		jdbctemplateobj.update("update dompet set saldo = saldo - ? where dompetid = ?"
				,jumlah,dompetid);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updatesaldo(String jenis, String dompetid, int jumlah) {
		System.out.println(jenis);
		if(jenis.equalsIgnoreCase("tambahsaldo")) {
			return tambahsaldo(dompetid, jumlah);
		}else{			
			return kurangsaldo(dompetid, jumlah);
		}
	}

	@Override
	public ResponseEntity<Object> buatdompet(String username) {
		jdbctemplateobj.update("insert into dompet values(dompetid_seq.nextval,?,0)",username);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> hapusdompet(String username) {
		jdbctemplateobj.update("delete from dompet where username = ?",username);
		return null;
	}

	@Override
	public ResponseEntity<Daftarpinjamanuser> getdaftarpinjaman(String username) {
		List<Daftarpinjaman> daftarpinjaman = jdbctemplateobj.query("select * from daftarpeminjaman where \"username\" = ?",
				new Object[] {username}, new Daftarpinjamanmapper());
		Daftarpinjamanuser daftaruser = new Daftarpinjamanuser();
		daftaruser.setUsername(username);
		daftaruser.setDaftarpinjaman((ArrayList<Daftarpinjaman>) daftarpinjaman);
		return new ResponseEntity<>(daftaruser,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Dompet> getDompet(String username) {
		return new ResponseEntity<>(jdbctemplateobj.queryForObject("select * from dompet where username = ? ",
				new Object[] {username}, new Dompetmapper()),HttpStatus.OK);
	}
	
	

}

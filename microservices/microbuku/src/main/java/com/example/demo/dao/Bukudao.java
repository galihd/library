package com.example.demo.dao;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Buku;
import com.example.demo.model.BukuMapper;
import com.example.demo.model.BukuMapper2;

@Repository("bukudb")
public class Bukudao implements BukudaoInt{
	@Bean
	static DataSource getDataSource() {
		return DataSourceBuilder.create()
				.url("jdbc:oracle:thin:@//localhost:1521/XEPDB1")
				.username("microbuku")
				.password("mstest")
				.build();
	}
	
	
	@Autowired
	static JdbcTemplate jdbctemplateobj = new JdbcTemplate(getDataSource());
	
	static SimpleJdbcCall procedure = new SimpleJdbcCall(jdbctemplateobj).withProcedureName("tambahbuku_proc");
	static LobHandler lobhandler = new DefaultLobHandler();
	
	@Override
	public ResponseEntity<Buku> getBuku(String judul) {
		System.out.println(judul);
		List<Buku> listbuku = jdbctemplateobj
				.query("select bukuid,judul,tahunterbit,genre,harga,transid,tanggalupdate from buku where judul  = ? "
				, new Object[] {judul}, new BukuMapper2());
		return new ResponseEntity<>(DataAccessUtils.requiredSingleResult(listbuku),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Resource> bacabuku(String judul) throws IOException {
		List<Buku> query = jdbctemplateobj.query("select * from buku where judul = ?"
				, new Object[] {judul}, new BukuMapper());
		Buku buku = DataAccessUtils.requiredSingleResult(query);
		InputStreamResource inputsr = new InputStreamResource(
				new ByteArrayInputStream(buku.getFile()));
		HttpHeaders responseheader = new HttpHeaders();
		responseheader.setContentType(MediaType.APPLICATION_PDF);
		responseheader.setContentLength(buku.getFile().length);
		return new ResponseEntity<Resource>(inputsr,responseheader,HttpStatus.OK);			
				
	}

	@Override
	public int tambahbuku(String transid, Buku buku,MultipartFile file) throws IOException {
		if(!bukuexist(buku.getJudul())) {
		buku.setFile(file.getBytes());
		File saved = new File("D:\\KULIAH\\Semester 5\\UploadedFile\\" + buku.getJudul()+".pdf");
		FileOutputStream fos;

			fos = new FileOutputStream(saved);
			fos.write(buku.getFile());
			fos.close();

		
		buku.setTransid(transid);
		
		procedure.execute(new MapSqlParameterSource()
				.addValue("bjudul", buku.getJudul())
				.addValue("bpenulis", buku.getPenulis())
				.addValue("bthn", buku.getTahun())
				.addValue("bgenre", buku.getGenre())
				.addValue("bharga", buku.getHarga())
				.addValue("btransid", buku.getTransid())
				.addValue("bufile", 
						new SqlLobValue(new ByteArrayInputStream(buku.getFile())
								,buku.getFile().length
								,lobhandler))
				);
		return 200;
		}else {
			return 400;
		}
	}

	@Override
	public int hapusBuku(String bukuid) {
		// TODO Auto-generated method stub
		return 0;
		
	}

	@Override
	public int updatebuku(Buku buku) {
		return 0;
	}

	@Override
	public boolean bukuexist(String judulbuku) {
		int exist = jdbctemplateobj.queryForObject("select count(*) from buku where judul = ?",new Object[] {judulbuku},Integer.class);
		return exist > 0;
	}

	@Override
	public ResponseEntity<List<String>> Searchbuku(String query) {
		List<String> result = new ArrayList<>();
		if(Suggest(query).getBody().size() < 1) {
			result = jdbctemplateobj.queryForList("select judul from buku where judul like %"+"?"+"%",
					new Object[] {query},String.class);
			return new ResponseEntity<List<String>>(result,HttpStatus.OK);
		}else {
			return Suggest(query);
		}
		
	}

	@Override
	public ResponseEntity<List<String>> Suggest(String query) {
		List<String> result = jdbctemplateobj.queryForList("select judul from buku where judul like ?",
				new Object[] {query +"%"},String.class);
		return new ResponseEntity<List<String>>(result,HttpStatus.OK);
	}
	

	
	

}

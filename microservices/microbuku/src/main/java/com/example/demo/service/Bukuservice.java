package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BukudaoInt;
import com.example.demo.model.Buku;
import com.example.demo.model.BukuInfo;

@Service
@Transactional
public class Bukuservice implements BukuserviceInt{
	@Bean
	static DataSource getDataSource() {
		return DataSourceBuilder.create()
				.url("jdbc:oracle:thin:@//localhost:1521/XEPDB1")
				.username("microbuku")
				.password("mstest")
				.build();
	}
	
	@Autowired
	private final BukudaoInt bukudao;
	
	public Bukuservice(BukudaoInt bukudao) {
		this.bukudao = bukudao;
	}

	@Override
	public ResponseEntity<Buku> getBookInfo(String judul) {
		return new ResponseEntity<Buku>((Buku)bukudao.getOneByJudul(judul),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Resource> bacaBuku(String judul) throws IOException {
		Buku buku = bukudao.getOne(judul);
		InputStreamResource resource = new InputStreamResource(
				new ByteArrayInputStream(
						buku.getFile())
				);
		
		HttpHeaders responseheaders = new HttpHeaders();
		responseheaders.setContentType(MediaType.APPLICATION_PDF);
		responseheaders.setContentLength(buku.getFile().length);		
		return new ResponseEntity<Resource>(resource,responseheaders, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> tambahBuku(Buku buku, MultipartFile file) throws IOException {
		if(!bukudao.existsById(buku.getJudul())) {
			buku.setFile(file.getBytes());
				//save file//
				File saved = new File("D:\\KULIAH\\Semester 5\\UploadedFile\\" + buku.getJudul()+".pdf");
				FileOutputStream fos;			
				fos = new FileOutputStream(saved);
				fos.write(buku.getFile());
				fos.close();
		
			bukudao.save(buku);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> updateHarga(Buku buku) {
		bukudao.updateHarga(buku.getJudul(),buku.getHarga());
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BukuInfo>> searchBuku(String judul,String fixed) {
		if(fixed.equalsIgnoreCase("false")) {
			return new ResponseEntity<List<BukuInfo>>(bukudao.searchBuku(judul+"%"),HttpStatus.OK);
		}else {
			return new ResponseEntity<List<BukuInfo>>(bukudao.searchBuku("%"+judul+"%"),HttpStatus.OK);
		}
	}

	@Override
	public ResponseEntity<Object> updateBuku(Buku buku) {
		Buku updatebuku = bukudao.getOne(buku.getJudul());
		updatebuku.setGenre(buku.getGenre());
		updatebuku.setHarga(buku.getHarga());
		updatebuku.setPenulis(buku.getPenulis());
		updatebuku.setTahun(buku.getTahun());
		bukudao.save(updatebuku);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BukuInfo>> findByTahun(String tahun) {		
		return new ResponseEntity<List<BukuInfo>>(bukudao.findByTahun(tahun),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<BukuInfo>> findByGenre(String genre) {
		return new ResponseEntity<List<BukuInfo>>(bukudao.findByGenre(genre),HttpStatus.OK);
	}
	
	

}

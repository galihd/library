package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.BukudaoInt;
import com.example.demo.model.Buku;

@Service
public class Bukuservice {
	@Autowired
	private final BukudaoInt bukudao;
	
	public Bukuservice(@Qualifier("bukudb")BukudaoInt bukudao) {
		this.bukudao = bukudao;
	}
	public ResponseEntity<Resource> bacabuku(String judul) throws IOException{
		return bukudao.bacabuku(judul);
	}
	
	public int tambahbuku(String transid, Buku buku,MultipartFile file) throws IOException {
		return bukudao.tambahbuku(transid, buku,file);
	}
	
	public ResponseEntity<Buku> getbuku(String judul) {
		return bukudao.getBuku(judul);
	}
	
	public ResponseEntity<List<String>> searchbuku(String query){
		return bukudao.Searchbuku(query);
	}
	
	public ResponseEntity<List<String>> suggest(String query){
		return bukudao.Suggest(query);
	}
}

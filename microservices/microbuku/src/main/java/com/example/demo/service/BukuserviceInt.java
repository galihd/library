package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Buku;
import com.example.demo.model.BukuInfo;

public interface BukuserviceInt {
	ResponseEntity<BukuInfo> getBookInfo(String bukuid);
	ResponseEntity<Resource> bacaBuku(String bukuid) throws IOException;
	ResponseEntity<Object> tambahBuku(Buku buku,MultipartFile file) throws IOException;
	ResponseEntity<Object> updateHarga(Buku buku);
	ResponseEntity<Object> updateBuku(Buku buku);
	ResponseEntity<List<BukuInfo>> searchBuku(String judul,String fixed);
	ResponseEntity<List<BukuInfo>> findByTahun(String tahun);
	ResponseEntity<List<BukuInfo>> findByGenre(String genre);
}

package com.example.demo.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Buku;


public interface BukudaoInt {
public int tambahbuku(String transid,Buku buku,MultipartFile file) throws IOException;
public int hapusBuku(String bukuid);
public int updatebuku(Buku buku);
public boolean bukuexist(String bukuid);
public ResponseEntity<Resource> bacabuku(String id) throws IOException;
public ResponseEntity<Buku> getBuku(String judul);
public ResponseEntity<List<String>> Searchbuku(String query);
public ResponseEntity<List<String>> Suggest(String query);
}

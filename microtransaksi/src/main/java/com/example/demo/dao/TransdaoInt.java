package com.example.demo.dao;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Transaksi;

public interface TransdaoInt {
public ResponseEntity<Object> trans(String dompetid,String bukujson, MultipartFile file,Object ... args) throws RestClientException, IOException;
public List<Transaksi> getdaftarTransaksi(String dompetid);
public Transaksi getTransbyId(String transid);
}

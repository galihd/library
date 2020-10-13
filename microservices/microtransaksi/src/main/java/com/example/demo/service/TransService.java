package com.example.demo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.TransdaoInt;
import com.example.demo.model.Transaksi;

@Service
public class TransService {
	@Autowired
	private final TransdaoInt transdao;
	
	public TransService(@Qualifier("transdb")TransdaoInt transdao) {
		this.transdao = transdao;
	}
	
	public ResponseEntity<Object> trans(String dompetid,String jsonbuku,MultipartFile file,Object... args)
			throws RestClientException, IOException {
		if(jsonbuku != null && !jsonbuku.isEmpty() && file.getSize() != 0)
			return transdao.trans(dompetid,jsonbuku,file,args);
		
		
		return transdao.trans(dompetid,null,null,args);
	}

	public List<Transaksi> getdaftarTransaksi(String dompetid) {
		return transdao.getdaftarTransaksi(dompetid);
	}
	
	
}

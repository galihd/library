package com.example.demo.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Transaksi;
import com.example.demo.service.TransService;

@RestController
@RequestMapping("/{id}")
public class Transcontroller {
	
	@Autowired
	private final TransService transservice;
	public Transcontroller(TransService transservice) {
		this.transservice = transservice;
	}
	
	@PostMapping
	public ResponseEntity<Object> trans(@PathVariable("id")String dompetid
			,@RequestParam(value = "buku",required = false)String jsonbuku
			,@RequestPart(value = "file",required = false) MultipartFile file			
			,@RequestParam("args")  Object... args
			) throws RestClientException, IOException {
		if(jsonbuku != null)
			return transservice.trans(dompetid,jsonbuku,file,args);
		
		
		return transservice.trans(dompetid,null,null,args);
	}
	
	@GetMapping
	public List<Transaksi> getdaftarTransaksi(@PathVariable ("id") String dompetid){
		return transservice.getdaftarTransaksi(dompetid);
	}
	
	
	
	
}

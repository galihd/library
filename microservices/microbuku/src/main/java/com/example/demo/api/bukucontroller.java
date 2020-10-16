package com.example.demo.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Buku;
import com.example.demo.model.BukuInfo;
import com.example.demo.service.Bukuservice;
import com.example.demo.service.BukuserviceInt;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@RestController
public class bukucontroller {
	@Autowired
	private final BukuserviceInt bukuservice;
	
	public bukucontroller(Bukuservice bukuservice) {
		this.bukuservice = bukuservice;
	}

	@GetMapping(path="/{judul}")
	public ResponseEntity<Resource> bacabuku(@PathVariable("judul") String judul) throws IOException{
		return bukuservice.bacaBuku(judul);
	}
	
	@GetMapping
	public ResponseEntity<Buku> getbuku(@RequestParam(value = "judul") String judul) {
		return bukuservice.getBookInfo(judul);
	}
	
	@GetMapping(path = "/tahun")
	public ResponseEntity<List<BukuInfo>> findByTahun(@RequestParam String tahun){
		return bukuservice.findByTahun(tahun);
	}
	
	@GetMapping(path = "/genre")
	public ResponseEntity<List<BukuInfo>> findByGenre(@RequestParam String genre){
		return bukuservice.findByGenre(genre);
	}
	
	@PostMapping
	public ResponseEntity<Object> tambahbuku(
			@RequestParam(value = "buku") String bukujson,
			@RequestParam(value = "file") MultipartFile file) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.registerModule(new ParameterNamesModule(Mode.PROPERTIES));
		Buku buku = mapper.readValue(bukujson, Buku.class);
		System.out.println(bukujson);
		System.out.println("size = " + file.getSize());
		return (bukuservice.tambahBuku(buku, file));
	}
	
	@PutMapping
	public ResponseEntity<Object> updateBuku(@RequestBody Buku buku){
		return bukuservice.updateBuku(buku);
	}
	
	@GetMapping(path= "/searchbuku")
	public ResponseEntity<List<String>> searchbuku(@RequestParam(value = "query") String searchquery,
												   @RequestParam(value = "final") String fixed){
		return bukuservice.searchBuku(searchquery,fixed);
	}

	
}

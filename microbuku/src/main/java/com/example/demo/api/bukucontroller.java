package com.example.demo.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Buku;
import com.example.demo.service.Bukuservice;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@RestController
public class bukucontroller {
	@Autowired
	private final Bukuservice bukuservice;
	
	public bukucontroller(Bukuservice bukuservice) {
		this.bukuservice = bukuservice;
	}

	@GetMapping(path="/{judul}")
	public ResponseEntity<Resource> bacabuku(@PathVariable("judul") String judul) throws IOException{
		return bukuservice.bacabuku(judul);
	}
	
	@GetMapping(produces = "application/json")
	public ResponseEntity<Buku> getbuku(@QueryParam("judul") String judul) {
		return bukuservice.getbuku(judul);
	}
	
	@PostMapping(path="/{id}")
	public void tambahbuku(@PathVariable("id") String transid,
			@RequestParam(value = "buku") String bukujson,
			@RequestParam(value = "file") MultipartFile file,
			HttpServletResponse resp) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		mapper.registerModule(new ParameterNamesModule(Mode.PROPERTIES));
		Buku buku = mapper.readValue(bukujson, Buku.class);
		System.out.println(bukujson);
		System.out.println("size = " + file.getSize());
		resp.setStatus(bukuservice.tambahbuku(transid,buku,file));
	}
	
	@GetMapping(path= "/suggest")
	public ResponseEntity<List<String>> suggest(@RequestParam(value = "searchquery") String query){
		return bukuservice.suggest(query);
	}
	@GetMapping(path = "/search")
	public ResponseEntity<List<String>> searchbuku(@RequestParam(value = "searchquery") String query){
		return bukuservice.searchbuku(query);
	}
	
}

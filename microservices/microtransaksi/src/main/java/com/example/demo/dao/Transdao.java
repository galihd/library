package com.example.demo.dao;


import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.model.Buku;
import com.example.demo.model.Transaksi;
import com.example.demo.model.TransaksiMapper;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Repository("transdb")
public class Transdao implements TransdaoInt{
	
	@Bean static DataSource getdatasource() {
	return DataSourceBuilder.create()
			.url("jdbc:oracle:thin:@//localhost:1521/XEPDB1")
			.username("microtrans")
			.password("mstest")
			.build();
	}
	
	@Autowired
	static JdbcTemplate jdbctemplateobj = new JdbcTemplate(getdatasource());
	
	@Autowired
	RestTemplate rt;
//	@Autowired
//	KafkaTemplate<String, Buku> bukutemplate;
	@Autowired
	WebClient.Builder webclient;
	
	@Override
	public ResponseEntity<Object> trans(String dompetid,String bukujson,MultipartFile file,Object... args) 
			throws RestClientException, IOException {
		if(String.valueOf(args[0]).equalsIgnoreCase("topup") && args.length == 2) {
			//---------------TAMBAHSALDO
			ResponseEntity<Object> response = updatesaldo(dompetid, "tambahsaldo", Integer.parseInt(String.valueOf(args[1])));
			
			if(response.getStatusCodeValue() == 200)
			jdbctemplateobj.update("insert into transaksi values(tid_seq.nextval,?,?,?,(select sysdate from dual),null,null)"
					,dompetid
					,String.valueOf(args[0])
					,Integer.parseInt(String.valueOf(args[1]))
					);
	
			return response;			
		}
		if(String.valueOf(args[0]).equalsIgnoreCase("pinjam") && args.length == 3) {
			System.out.println("pinjam ===");
			//---------------getbuku
			
			String json = rt.getForObject("http://microbuku?judul="+String.valueOf(args[1]), String.class);
			
			ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			
			mapper.registerModule(new ParameterNamesModule(Mode.PROPERTIES));
			
			Buku book = mapper.readValue(json, Buku.class);
			

			int lamapinjam = Integer.parseInt(String.valueOf(args[2]));
			//---------------kurangsaldo
			updatesaldo(dompetid, "kurangsaldo", book.getHarga()*lamapinjam);
			
			//---------------insert
			jdbctemplateobj.update("insert into transaksi values(tid_seq.nextval,?,?,?,(select sysdate from dual),?,?)"
					,dompetid
					,String.valueOf(args[0])
					,book.getHarga()
					,lamapinjam
					,String.valueOf(args[1]));
			
			
			//---------------tambahsaldo
			String ownerid = jdbctemplateobj.queryForObject("select dompetid from transaksi where transid = ?",
					new Object[] {book.getTransid()}
			,String.class);
			
			
			
			return updatesaldo(ownerid, "tambahsaldo", book.getHarga()*lamapinjam);
		}
		if(String.valueOf(args[0]).equalsIgnoreCase("tambah") && args.length == 1) {
			
			ObjectMapper mapper = new ObjectMapper()
					.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT
							,DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
			
			mapper.registerModule(new ParameterNamesModule(Mode.PROPERTIES));
			
			Buku buku = mapper.readValue(bukujson, Buku.class);
			//------------post
			String transid = jdbctemplateobj.queryForObject("select tid_seq.nextval from dual",String.class);
			buku.setTransid(transid);
			
			ByteArrayResource filecontent = new ByteArrayResource(file.getBytes()) {
				@Override
				public String getFilename() {
					return buku.getJudul();
				}
			};
			
			HttpHeaders bukuheaders = new HttpHeaders();
			bukuheaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Buku> bukurequest = new HttpEntity<>(buku,bukuheaders);
			
			HttpHeaders fileheaders = new HttpHeaders();
			fileheaders.setContentType(MediaType.APPLICATION_PDF);
			fileheaders.setContentLength(filecontent.contentLength());
			HttpEntity<ByteArrayResource> filerequest = new HttpEntity<>(filecontent,fileheaders);
			
			MultiValueMap<String, Object> mainrequestentity = new LinkedMultiValueMap<>();
			mainrequestentity.add("buku", bukurequest);
			mainrequestentity.add("file", filerequest);
			
			HttpHeaders mainheaders = new HttpHeaders();
			mainheaders.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<MultiValueMap<String, Object>> requestentity = new HttpEntity<>(mainrequestentity,mainheaders);
			
			ResponseEntity<Object> response = rt.postForEntity("http://microbuku/"
					,requestentity, Object.class);

			if (response.getStatusCodeValue() != 200) {
				return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
			}else {
				
			//-------------insert
				jdbctemplateobj.update("insert into transaksi values(?,?,?,?,(select sysdate from dual),null,?)"
						,transid
						,dompetid
						,String.valueOf(args[0])
						,buku.getHarga()
						,buku.getJudul());
				
				
				return response;
			}
		}
		if(String.valueOf(args[0]).equalsIgnoreCase("updateharga") && args.length == 2) {
			//-----------------putbuku
			return new ResponseEntity<Object>(args[0],HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
	}

	@Override
	public List<Transaksi> getdaftarTransaksi(String dompetid) {
		return jdbctemplateobj.query("select * from transaksi where dompet id = ?",
				new Object[]{dompetid}
				,new TransaksiMapper());
	}

	@Override
	public Transaksi getTransbyId(String transid) {
		return jdbctemplateobj.queryForObject("select * from transaksi where transid = ?"
				, new Object[] {transid}
				,new TransaksiMapper());
	}
	
	ResponseEntity<Object> updatesaldo(String dompetid,String jenis,int total) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return rt.exchange("http://microdompet/"+dompetid+"?jenis="+jenis+"&jumlah="+total,
				HttpMethod.PUT,null,Object.class);
				
	}
	
	
}

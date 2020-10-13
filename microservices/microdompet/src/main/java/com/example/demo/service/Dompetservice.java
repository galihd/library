package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DompetdaoInt;
import com.example.demo.model.Daftarpinjamanuser;
import com.example.demo.model.Dompet;

@Service
public class Dompetservice {
	
	@Autowired
	private final DompetdaoInt dompetdao;

	//KafkaTemplate<String, String> kafkatemplate;
	String Topic ="username";

	public Dompetservice(@Qualifier("dompetdb") DompetdaoInt dompetdao) {
		super();
		this.dompetdao = dompetdao;
	}
	
	//@KafkaListener(topics = "username", groupId = "dompet")
	public ResponseEntity<Object> buatdompet(String username) {
		System.out.println("username :" + username);
		return dompetdao.buatdompet(username);
	}
	
	public ResponseEntity<Object> updatesaldo(String jenis,String dompetid, int jumlah) {
		return dompetdao.updatesaldo(jenis, dompetid, jumlah);
	}
	public ResponseEntity<Dompet> getdompet(String username){
		return dompetdao.getDompet(username);
	}
	public ResponseEntity<Daftarpinjamanuser> getdaftarpinjaman(String username){
		return dompetdao.getdaftarpinjaman(username);
	}
}

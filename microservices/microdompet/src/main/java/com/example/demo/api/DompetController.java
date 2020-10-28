package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Daftarpinjamanuser;
import com.example.demo.model.Dompet;
import com.example.demo.service.Dompetservice;

@RestController
public class DompetController {

	@Autowired
	private final Dompetservice dompetservice;

	public DompetController(Dompetservice dompetservice) {
		this.dompetservice = dompetservice;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updatesaldo(
			@PathVariable("id") String dompetid,
			@RequestParam("jenis") String jenis,
			@RequestParam("jumlah") int jumlah) {
		
		System.out.println("dompetid :"+ dompetid);
		System.out.println("jumlah :"+ jumlah);
		System.out.println("jenis :"+ jenis);
		return dompetservice.updatesaldo(jenis, dompetid, jumlah);
	}
	@PostMapping()
	public ResponseEntity<Object> buatdompet(@RequestParam("username") String username) {
		return dompetservice.buatdompet(username);
	}
	
	@GetMapping(path = "/{username}")
	public ResponseEntity<Dompet> getdompet(@PathVariable("username") String username){
		System.out.println("called by : " + username );
		return dompetservice.getdompet(username);
	}
	
	@GetMapping(path = "/daftarpinjaman")
	public ResponseEntity<Daftarpinjamanuser> getdaftarpinjaman(@RequestParam(value = "username") String username){
		
		return dompetservice.getdaftarpinjaman(username);
	}
	
}

package com.example.demo.dao;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Daftarpinjamanuser;
import com.example.demo.model.Dompet;

public interface DompetdaoInt {
	ResponseEntity<Object> tambahsaldo(String dompetid,int jumlah);
	ResponseEntity<Object> kurangsaldo(String dompetid,int jumlah);
	ResponseEntity<Object> updatesaldo(String jenis,String dompetid,int jumlah);
	ResponseEntity<Object> buatdompet(String username);
	ResponseEntity<Object> hapusdompet(String username);
	ResponseEntity<Daftarpinjamanuser> getdaftarpinjaman(String username);
	ResponseEntity<Dompet> getDompet(String username);
}

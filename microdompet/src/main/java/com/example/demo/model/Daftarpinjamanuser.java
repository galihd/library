package com.example.demo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Daftarpinjamanuser {
	String username;
	ArrayList<Daftarpinjaman> daftarpinjaman;
	public Daftarpinjamanuser() {
			
		}
	
	public Daftarpinjamanuser(@JsonProperty("userlist")
	String username,
	@JsonProperty("contentlist")ArrayList<Daftarpinjaman> daftarpinjaman) {
		super();
		this.username = username;
		this.daftarpinjaman = daftarpinjaman;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public ArrayList<Daftarpinjaman> getDaftarpinjaman() {
		return daftarpinjaman;
	}
	
	public void setDaftarpinjaman(ArrayList<Daftarpinjaman> daftarpinjaman) {
		this.daftarpinjaman = daftarpinjaman;
	}
	
}

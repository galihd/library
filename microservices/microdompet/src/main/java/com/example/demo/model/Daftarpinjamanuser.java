package com.example.demo.model;

import java.util.ArrayList;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Daftarpinjamanuser {
	String username;
	ArrayList<Daftarpinjaman> daftarpinjaman;
	public Daftarpinjamanuser() {}

	public Daftarpinjamanuser(@JsonProperty("userlist") String username,
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

	public Daftarpinjamanuser username(String username) {
		this.username = username;
		return this;
	}

	public Daftarpinjamanuser daftarpinjaman(ArrayList<Daftarpinjaman> daftarpinjaman) {
		this.daftarpinjaman = daftarpinjaman;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Daftarpinjamanuser)) {
			return false;
		}
		Daftarpinjamanuser daftarpinjamanuser = (Daftarpinjamanuser) o;
		return Objects.equals(username, daftarpinjamanuser.username) && Objects.equals(daftarpinjaman, daftarpinjamanuser.daftarpinjaman);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, daftarpinjaman);
	}

	@Override
	public String toString() {
		return "{" +
			" username='" + getUsername() + "'" +
			", daftarpinjaman='" + getDaftarpinjaman() + "'" +
			"}";
	}



	
}

package com.example.demo.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Buku {
	String judul;
	String penulis;
	String tahun;
	String genre;
	byte[] file;
	int harga;
	String transid;
	Date tanggalupdate;
	
	@JsonCreator
	public Buku(
			@JsonProperty("judul") String judul,
			@JsonProperty("penulis")String penulis,
			@JsonProperty("tahun") String tahun,
			@JsonProperty("genre") String genre,
			byte[] file,
			@JsonProperty("harga") int harga,
			@JsonProperty("transid") String transid, 
			Date tanggalupdate) {
		super();
		this.judul = judul;
		this.penulis = penulis;
		this.tahun = tahun;
		this.genre = genre;
		this.file = file;
		this.harga = harga;
		this.transid = transid;
		this.tanggalupdate = tanggalupdate;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getPenulis() {
		return penulis;
	}

	public void setPenulis(String penulis) {
		this.penulis = penulis;
	}

	public String getTahun() {
		return tahun;
	}

	public void setTahun(String tahun) {
		this.tahun = tahun;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public int getHarga() {
		return harga;
	}

	public void setHarga(int harga) {
		this.harga = harga;
	}
	public String getTransid() {
		return transid;
	}
	
	@JsonProperty("transid")
	public void setTransid(String transid) {
		this.transid = transid;
	}

	public Date getTanggalupdate() {
		return tanggalupdate;
	}

	public void setTanggalupdate(Date tanggalupdate) {
		this.tanggalupdate = tanggalupdate;
	}
	
	
	@Override
	public String toString() {
		return "Buku [judul=" + judul + ", penulis=" + penulis + ", tahun=" + tahun + ", genre="
				+ genre + ", file=" + file + ", harga=" + harga + ", penambahanid=" + transid + ", tanggalupdate="
				+ tanggalupdate + "]";
	}

	public Buku() {		
	}
	
}
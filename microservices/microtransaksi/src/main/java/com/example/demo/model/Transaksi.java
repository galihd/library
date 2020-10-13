package com.example.demo.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaksi {
	String transid;
	String dompetid;
	String jenis;
	int total;
	Date tanggal;
	public Transaksi(@JsonProperty("transid") String transid
			,@JsonProperty("dompetid") String dompetid,
			@JsonProperty("jenis") String jenis,
			@JsonProperty("total") int total,
			@JsonProperty("tanggal") Date tanggal) {
		super();
		this.transid = transid;
		this.dompetid = dompetid;
		this.jenis = jenis;
		this.tanggal = tanggal;
		this.total = total;
	}
	
	public Transaksi() {}

	public String getTransid() {
		return transid;
	}
	
	public void setTransid(String transid) {
		this.transid = transid;
	}
	
	public String getDompetid() {
		return dompetid;
	}
	
	public void setDompetid(String dompetid) {
		this.dompetid = dompetid;
	}
	
	public String getJenis() {
		return jenis;
	}
	
	public void setJenis(String jenis) {
		this.jenis = jenis;
	}
	
	public Date getTanggal() {
		return tanggal;
	}
	
	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
}

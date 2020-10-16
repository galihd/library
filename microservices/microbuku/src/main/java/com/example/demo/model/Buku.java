package com.example.demo.model;




import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "buku")
@EntityListeners(AuditingEntityListener.class)
public class Buku implements BukuInfo{
	@Id
	@Column(name = "judul")
	String judul;
	
	@Column(name = "penulis")
	String penulis;
	
	@Column(name = "tahunterbit")
	String tahun;
	
	@Column(name = "genre")
	String genre;
	
	@Lob
	@Column(name = "filebuku")
	@JsonIgnore
	byte[] file;
	
	@Column(name = "harga")
	int harga;
	
	@Column(name = "transid")
	String transid;
	
	@LastModifiedDate
	@Column(name = "tanggalupdate")
	Date tanggalupdate;
	
	@JsonCreator
	public Buku(
			@JsonProperty("judul") String judul,
			@JsonProperty("penulis")String penulis,
			@JsonProperty("tahun") String tahun,
			@JsonProperty("genre") String genre,
			byte[] file,
			@JsonProperty("harga") int harga,
			@JsonProperty("transid")
			String transid, 
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

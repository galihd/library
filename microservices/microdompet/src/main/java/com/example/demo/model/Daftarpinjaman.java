package com.example.demo.model;
import java.sql.Date;

public class Daftarpinjaman {
String judulbuku;
Date tanggalpinjam;
int lamapinjam;
String transid;
public Daftarpinjaman() {}
public Daftarpinjaman(String judulbuku, Date tanggalpinjam, int lamapinjam,String transid) {
	this.judulbuku = judulbuku;
	this.tanggalpinjam = tanggalpinjam;
	this.lamapinjam = lamapinjam;
	this.transid = transid;
}
public String getJudulbuku() {
	return judulbuku;
}
public void setJudulbuku(String judulbuku) {
	this.judulbuku = judulbuku;
}
public Date getTanggalpinjam() {
	return tanggalpinjam;
}
public void setTanggalpinjam(Date tanggalpinjam) {
	this.tanggalpinjam = tanggalpinjam;
}
public int getLamapinjam() {
	return lamapinjam;
}
public void setLamapinjam(int lamapinjam) {
	this.lamapinjam = lamapinjam;
}

public String getTransid() {
	return transid;
}
public void setTransid(String transid) {
	this.transid = transid;
}
@Override
public String toString() {
	return "Daftarpinjaman [judulbuku=" + judulbuku + ", tanggalpinjam=" + tanggalpinjam + ", lamapinjam=" + lamapinjam
			+ ", transid=" + transid + "]";
}





}
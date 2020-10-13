package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Dompet {
	String dompetid;
	String username;
	int saldo;
	public Dompet() {}
	public Dompet(@JsonProperty("dompetid") String dompetid,
				  @JsonProperty("username") String username,
				  @JsonProperty("saldo") int saldo) {
		this.dompetid = dompetid;
		this.username = username;
		this.saldo = saldo;
	}
	public String getDompetid() {
		return dompetid;
	}
	public void setDompetid(String dompetid) {
		this.dompetid = dompetid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	@Override
	public String toString() {
		return "Dompet [dompetid=" + dompetid + ", username=" + username + ", saldo=" + saldo + "]";
	}
	
	
}

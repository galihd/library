package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cuser {

    String username;
    String pswd;
    String email;
	String roles;
    public Cuser(@JsonProperty("name")String username,
                 @JsonProperty("pwd")String pswd,
				 @JsonProperty("email")String email) {
        this.username = username;
        this.pswd = pswd;
		this.email = email;
    }

	@Override
	public String toString() {
		return "Cuser [username=" + username + ", pswd=" + pswd + ", email=" + email + ", roles ="+ roles+"]";
	}


	public String getRoles() {
		return this.roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Cuser() {}
    
}
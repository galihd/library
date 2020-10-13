package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CuserdaoInt;
import com.example.demo.model.Cuser;

@Service
public class CuserService {

	@Autowired
	private final CuserdaoInt cuserdao;

	public CuserService(@Qualifier("userdb") CuserdaoInt cuserdao) {
		this.cuserdao = cuserdao;
	}
	
	public int authenticateUser(Cuser user) {
		return cuserdao.authenticateUser(user);
	}
	
	public int registeruser(Cuser user) {
		return cuserdao.registerUser(user);
	}
	
	public int changepassword(Cuser user) {
		return cuserdao.Changepassword(user);
	}
	
	
}

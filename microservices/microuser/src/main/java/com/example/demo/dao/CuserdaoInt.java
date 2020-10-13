package com.example.demo.dao;

import com.example.demo.model.Cuser;

public interface CuserdaoInt {
	int registerUser(Cuser user);
	int Changepassword(Cuser user);
	boolean userexist(String username);
	Cuser finduserbyname(String username);
	int authenticateUser(Cuser user);
	int deleteUser(Cuser user);
}

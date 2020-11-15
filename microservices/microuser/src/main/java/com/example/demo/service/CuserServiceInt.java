package com.example.demo.service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Cuser;

public interface CuserServiceInt {
	ResponseEntity<Object> registerUser(Cuser user);
	ResponseEntity<Cuser> getUserInfo(String username);
	ResponseEntity<List<Cuser>> getAllUser();
	ResponseEntity<Object> deleteUser(Cuser user);
	ResponseEntity<Object> changePassword(Cuser user);
	ResponseEntity<Object> registerAdmin(Cuser user);
	ResponseEntity<?> userLogin(Cuser user) throws Exception;
}

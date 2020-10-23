package com.example.demo.api;

import com.example.demo.model.Cuser;
import com.example.demo.service.CuserServiceInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Cusercontroller {

	@Autowired
	private final CuserServiceInt cuserservice;
	
	
	public Cusercontroller(CuserServiceInt cuserservice) {
		this.cuserservice = cuserservice;
	}
	
	@PostMapping
	public ResponseEntity<Object> registerUser(@RequestBody Cuser user) {
		return (cuserservice.registerUser(user));
	}
	@PutMapping
	public ResponseEntity<Object> Changepassword(@RequestBody Cuser user) {
		return cuserservice.changePassword(user);
	}
	@PostMapping(path = "/login")
	public ResponseEntity<Object> authenticateUser(@RequestBody Cuser user) {
		return cuserservice.authenticateUser(user);
	}
	

}

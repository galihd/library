package com.example.demo.api;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cuser;
import com.example.demo.service.CuserService;


@RestController
public class Cusercontroller {

	@Autowired
	private final CuserService cuserservice;
	
	
	public Cusercontroller(CuserService cuserservice) {
		this.cuserservice = cuserservice;
	}
	
	@PostMapping
	public void registerUser(@RequestBody Cuser user,HttpServletResponse res) {
		res.setStatus(cuserservice.registeruser(user));
	}
	@PutMapping
	public void Changepassword(@RequestBody Cuser user,HttpServletResponse res) {
		res.setStatus(cuserservice.changepassword(user));
	}
	@PostMapping(path = "/login")
	public void authenticateUser(@RequestBody Cuser user, HttpServletResponse res ) {
		res.setStatus(cuserservice.authenticateUser(user));
	}
	

}

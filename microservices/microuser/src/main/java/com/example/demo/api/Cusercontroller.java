package com.example.demo.api;

import com.example.demo.model.Cuser;
import com.example.demo.service.CuserServiceInt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Cusercontroller {

	@Autowired
	private final CuserServiceInt cuserservice;
	
	public Cusercontroller(CuserServiceInt cuserservice) {
		this.cuserservice = cuserservice;
	}

	@PostMapping
	public ResponseEntity<Object> registerUser(@RequestBody Cuser user,@RequestParam(name = "admin",required = false) Boolean admin) {
		if(!admin)
		return (cuserservice.registerUser(user));

		return(cuserservice.registerAdmin(user));
	}

	@PutMapping
	public ResponseEntity<Object> Changepassword(@RequestBody Cuser user) {
		return cuserservice.changePassword(user);
	}
	
	// @PostMapping(path = "/login")
	// public ResponseEntity<?> userLogin(@RequestBody Cuser user) throws Exception{
	// 	return cuserservice.userLogin(user);
	// }

	@GetMapping(path = "/{username}")
	public ResponseEntity<Cuser> getUserInfo(@PathVariable("username") String username) {
		return cuserservice.getUserInfo(username);
	}
	

}

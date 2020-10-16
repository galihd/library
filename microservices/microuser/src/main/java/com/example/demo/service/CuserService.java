package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dao.CuserdaoInt;
import com.example.demo.model.Cuser;

@Service
public class CuserService implements CuserServiceInt{
	@Autowired
	private final CuserdaoInt cuserdao;
	
	@Autowired
	RestTemplate rt = new RestTemplate();
	
	public CuserService(CuserdaoInt cuserdao) {
		super();
		this.cuserdao = cuserdao;
	}

	@Override
	public ResponseEntity<Object> registerUser(Cuser user) {
		if(!cuserdao.findById(user.getUsername()).isPresent()) {
			cuserdao.save(user);
			return rt.postForEntity("http://microdompet?username="+user.getUsername(), null, Object.class);
		}else {
			return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Cuser> getUserInfo(String username) {
		return new ResponseEntity<Cuser>(cuserdao.getOne(username),HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Cuser>> getAllUser() {
		return new ResponseEntity<List<Cuser>>(cuserdao.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> authenticateUser(Cuser user) {
		if(cuserdao.getOne(user.getUsername()).getPswd().equals(user.getPswd()))
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		
		return new ResponseEntity<Object>(null,HttpStatus.CONFLICT);
	}

	@Override
	public ResponseEntity<Object> deleteUser(Cuser user) {
		cuserdao.delete(user);
		return new ResponseEntity<Object>(null,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> changePassword(Cuser user) {
		if(cuserdao.findById(user.getUsername()).isPresent()) {
			cuserdao.save(user);
			return new ResponseEntity<Object>(null,HttpStatus.OK);
		}else {
			return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
		}
	}


	
	
}

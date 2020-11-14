package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.dao.CuserdaoInt;
import com.example.demo.model.Cuser;
import com.example.demo.model.CuserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CuserService implements CuserServiceInt,UserDetailsService {
	@Autowired
	private final CuserdaoInt cuserdao;

	@Autowired
	private PasswordEncoder bcrypt;

	@Autowired
	RestTemplate rt = new RestTemplate();

	public CuserService(CuserdaoInt cuserdao) {
		super();
		this.cuserdao = cuserdao;
	}

	@Override
	public ResponseEntity<Object> registerUser(Cuser user) {
		if (!cuserdao.findById(user.getUsername()).isPresent()) {
			ResponseEntity<Object> response = rt.postForEntity("http://microdompet?username=" + user.getUsername(),
					null, Object.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				user.setRoles("member");
				user.setPswd(bcrypt.encode(user.getPswd()));
				cuserdao.save(user);
				return response;
			} else {
				return response;
			}
		} else {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Cuser> getUserInfo(String username) {
		return new ResponseEntity<Cuser>(cuserdao.getOne(username), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Cuser>> getAllUser() {
		return new ResponseEntity<List<Cuser>>(cuserdao.findAll(), HttpStatus.OK);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username = " + username);
		Cuser userinfo = cuserdao.findById(username).get();
		System.out.println("password = " + userinfo.getPswd());
		return new CuserDetails(userinfo);
	}

	@Override
	public ResponseEntity<Object> deleteUser(Cuser user) {
		cuserdao.delete(user);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> changePassword(Cuser user) {
		Optional<Cuser> updateuser;
		if ((updateuser = cuserdao.findById(user.getUsername())).isPresent()) {
			updateuser.get().setPswd(user.getPswd());
			cuserdao.save(updateuser.get());
			return new ResponseEntity<Object>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Object> registerAdmin(Cuser user) {
		if (!cuserdao.findById(user.getUsername()).isPresent()) {
			System.out.println("execute registeradmin");
			ResponseEntity<Object> response = rt.postForEntity("http://microdompet?username=" + user.getUsername(),
					null, Object.class);
			if (response.getStatusCode().equals(HttpStatus.OK)) {
				user.setRoles("admin");
				user.setPswd(bcrypt.encode(user.getPswd()));
				cuserdao.save(user);
				return response;
			} else {
				return response;
			}
		} else {
			return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
		}
	}
}

package com.example.demo.dao;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Cuser;
import com.example.demo.model.Cusermapper;

@Repository("userdb")
public class Cuserdao implements CuserdaoInt{
	@Bean
	static DataSource getDatasource() {
		return DataSourceBuilder.create()
				.url("jdbc:oracle:thin:@//localhost:1521/XEPDB1")
				.username("microuser")
				.password("mstest")
				.build();
	}
	
	@Autowired
	static JdbcTemplate jdbctemplateobj = new JdbcTemplate(getDatasource());
	@Autowired 
	private RestTemplate rt = new RestTemplate();

	@Override
	public int registerUser(Cuser user) {
		System.out.println(user.toString());
		if(!userexist(user.getUsername())) {
			jdbctemplateobj.update("insert into cuser values(?,?,?)"
					,user.getUsername()
					,user.getPswd()
					,user.getEmail());
			rt.postForEntity("http://microdompet?username="+user.getUsername(), null, Object.class);
			
			
			return 200;
		}
		else {
			return 405;
		}
	}
	

	@Override
	public int Changepassword(Cuser user) {
		jdbctemplateobj.update("update cuser set pswd = ? where username = ?"
				,user.getPswd(),user.getUsername());
		return 200;
	}

	@Override
	public boolean userexist(String username) {
		return jdbctemplateobj.queryForObject("select count(*) from Cuser where username = ?",
				new Object[] {username},Integer.class) > 0;
	}

	@Override
	public Cuser finduserbyname(String username) {
		return jdbctemplateobj.queryForObject("select * from Cuser where username = ?" ,new Object[] {username}
		,new Cusermapper());
	}

	@Override
	public int authenticateUser(Cuser user) {
		if(finduserbyname(user.getUsername()).getPswd().equals(user.getPswd())) {
			return 200;
		}else {
			return 400;
		}
	}

	@Override
	public int deleteUser(Cuser user) {
		if(userexist(user.getUsername())) {
			jdbctemplateobj.update("delete from cuser where username = ?",
					user.getUsername());
			return 200;
		}
		return 400;
	}

}

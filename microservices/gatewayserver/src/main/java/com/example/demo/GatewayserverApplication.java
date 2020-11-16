package com.example.demo;

import com.example.demo.Model.Cuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class GatewayserverApplication {

	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping(value = "/")
	public String HelloClient (){
		return "Hello Client";
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> userLogin(Cuser user) throws Exception{
		UsernamePasswordAuthenticationToken loginToken = 
		new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPswd());
		try {
			Authentication data = authenticationManager.authenticate(loginToken);
			if(data.isAuthenticated()){
				//Generate JWT
				return ResponseEntity.ok("login successful");
			}
		} catch (BadCredentialsException e) {
			throw new Exception("invalid username or password",e);
		}

		return ResponseEntity.badRequest().body(null);
	}
	@Bean
	public RouteLocator routelocator(RouteLocatorBuilder builder) {
		return builder.routes().build();
	}

	@Bean
	public WebClient.Builder webClient(){
		return WebClient.builder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	

}

package com.example.demo;

import com.example.demo.Model.Cuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class GatewayserverApplication {
	@GetMapping(value = "/")
	public String HelloClient (){
		return "Hello Client";
	}

	@Autowired
	ReactiveAuthenticationManager authenticationManager;

	@PostMapping(value = "/login")
	public Mono<ResponseEntity<?>> userLogin(@RequestBody Cuser user) throws Exception {
		UsernamePasswordAuthenticationToken loginToken = 
		new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPswd());
		try {
		return authenticationManager.authenticate(loginToken).flatMap((auth)->{
			System.out.println("authenticated ? : " + auth.isAuthenticated());
				if(auth.isAuthenticated()){
					return Mono.just(ResponseEntity.ok("successfully logged in"));
				}else{
					return Mono.just(ResponseEntity.badRequest().body("invalid Credentials"));
				}
			});	
		} catch (BadCredentialsException e) {
			throw new Exception("invalid username or password",e);
		}
	}
	@Bean
	public RouteLocator routelocator(RouteLocatorBuilder builder) {
		return builder.routes().build();
	}

	@Bean
	@LoadBalanced
	public WebClient.Builder webClient(){
		return WebClient.builder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	

}

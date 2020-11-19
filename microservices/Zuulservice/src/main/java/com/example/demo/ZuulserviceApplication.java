package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@RestController
public class ZuulserviceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@GetMapping(path = "/")
	public String helloClient(){
		return "hello client";
	}

	@GetMapping(path = "/login")
	public String oauthlogin(){
		// RestTemplate rt = restTemplate();
		// MultiValueMap<String,String> requestheaders = new LinkedMultiValueMap<>();
		// requestheaders.add("authorization", "basic dHVnYXNjbG91ZDp0dWdhc2Nsb3Vk");
		// requestheaders.add("username", "admin");
		// requestheaders.add("password", "admin");
		// requestheaders.add("grant_type", "password");

		// HttpHeaders requesthHeaders = new HttpHeaders(requestheaders);
		// requesthHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		// System.out.println(rt.exchange("http://microuser/oauth/token", HttpMethod.POST
		// , new HttpEntity<MultiValueMap<String,String>>(requestheaders, requesthHeaders)
		// , String.class).getBody().toString());
		return "??";
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}

}

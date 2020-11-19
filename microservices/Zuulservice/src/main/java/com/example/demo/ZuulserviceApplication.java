package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@RestController
public class ZuulserviceApplication {

	@GetMapping(path = "/")
	public String helloClient(){
		return "hello client";
	}

	public static void main(String[] args) {
		SpringApplication.run(ZuulserviceApplication.class, args);
	}

}

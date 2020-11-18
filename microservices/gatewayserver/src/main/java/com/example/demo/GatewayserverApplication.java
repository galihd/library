package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class GatewayserverApplication {
	
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

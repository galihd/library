package com.example.demo;

import com.example.demo.Filter.CustomGlobalFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class GatewayserverApplication {

	@GetMapping(value = "/")
	public String HelloClient (){
		return "Hello Client";
	}
	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}
	
	@Bean
	public RouteLocator routelocator(RouteLocatorBuilder builder) {
		return builder.routes().build();
	}

	// @Bean
	// GlobalFilter customfilter(){
	// 	return new CustomGlobalFilter();
	// }

    // @Bean
    // PasswordEncoder passwordEncoder(){
    //     return new BCryptPasswordEncoder(10);
    // }
}

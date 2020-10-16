package com.example.demo;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
public class MicrouserApplication {
	@Bean
	@LoadBalanced 
	public RestTemplate resttemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public DataSource datasource() {
		return DataSourceBuilder.create()
				.url("jdbc:oracle:thin:@//localhost:1521/XEPDB1")
				.username("microuser")
				.password("mstest")
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(MicrouserApplication.class, args);
		
	}
	
	

}

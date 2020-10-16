package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
@EnableJpaAuditing
public class MicrobukuApplication {
	@Bean
	@LoadBalanced
	RestTemplate resttemplate() {
		return new RestTemplate();
	}

	
	
	public static void main(String[] args) {
		SpringApplication.run(MicrobukuApplication.class, args);
	}


	
}

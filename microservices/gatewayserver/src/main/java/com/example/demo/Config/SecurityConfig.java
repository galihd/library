package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain webFilterChain (ServerHttpSecurity http){
        http.authorizeExchange(ex ->
        ex.pathMatchers(HttpMethod.POST,"/user/**").permitAll()
        .pathMatchers("/").permitAll()
        .anyExchange().authenticated()
        .and().httpBasic()
        );
        return http.build();
    }
}

   
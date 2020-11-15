package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain webFilterChain (ServerHttpSecurity http){
        http
        .formLogin().disable()
        .csrf().disable()
        .authorizeExchange(ex ->
        ex
            .pathMatchers("/user/**").permitAll()
            .pathMatchers("/").permitAll()
            .pathMatchers("/dompet/**").hasAnyAuthority("member,admin")
            .pathMatchers("/buku/**").hasAnyAuthority("member,admin")
            .pathMatchers("/transaksi/**").hasAnyAuthority("member,admin")
        .anyExchange().authenticated()
        .and().httpBasic().disable()
        );
        return http.build();
    }
}

   
package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig{

    @Autowired
    ReactiveUserDetailsService myUserDetailsService;

    @Bean
    public SecurityWebFilterChain webFilterChain (ServerHttpSecurity http){
        http
        .formLogin().disable()
        .csrf().disable()
        .authorizeExchange(ex ->
        ex
            .pathMatchers("/","/login").permitAll()
            .pathMatchers("/user/**").hasAnyAuthority("member,admin")
            .pathMatchers("/dompet/**").hasAnyAuthority("member,admin")
            .pathMatchers("/buku/**").hasAnyAuthority("member,admin")
            .pathMatchers("/transaksi/**").hasAnyAuthority("member,admin")
        .anyExchange().authenticated()
        .and().httpBasic()
        );
        return http.build();
    }

    @Bean
    PasswordEncoder BcryptEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    ReactiveAuthenticationManager authenticationManager(){
        UserDetailsRepositoryReactiveAuthenticationManager userDetailsRepository = 
        new UserDetailsRepositoryReactiveAuthenticationManager(myUserDetailsService);

        userDetailsRepository.setPasswordEncoder(BcryptEncoder());

        return userDetailsRepository;
    }
}

   
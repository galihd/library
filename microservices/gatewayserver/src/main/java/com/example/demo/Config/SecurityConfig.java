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
        http
        .authorizeExchange(ex ->
        ex.pathMatchers(HttpMethod.POST,"/user/login","/user").permitAll()
        .pathMatchers("/").permitAll()
        .anyExchange().authenticated()
        .and().httpBasic().disable())
        .csrf().disable();
        return http.build();
    }

    // @Bean
    // MapReactiveUserDetailsService userDetailsService(){
    //     RestTemplate rt =  new RestTemplate();
    //     Cuser user = rt.exchange("http://microuser/",HttpMethod.GET,null,Cuser.class).getBody();
    //     return new MapReactiveUserDetailsService(new Cuserdetails(user));
    // }
}

   
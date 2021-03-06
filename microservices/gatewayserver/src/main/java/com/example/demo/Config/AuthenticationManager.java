package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

@Component
public class Authenticationmanager implements ReactiveAuthenticationManager{
    @Autowired
    private ReactiveUserDetailsService myUserDetailsService;

    @Autowired
    PasswordEncoder bcryptencoder;


    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        System.out.println("authentication principal = " + authentication.getPrincipal().toString());
        System.out.println("authentication Credentials = " + authentication.getCredentials());
        Mono<UserDetails> user = myUserDetailsService.findByUsername(authentication.getPrincipal().toString());
        return user.flatMap(userdetails -> {
            if(bcryptencoder.matches(authentication.getCredentials().toString(),userdetails.getPassword())){
                authentication.setAuthenticated(true);
                return Mono.just(authentication);
            }else{
                return Mono.empty();
            }   
        });
    }
}
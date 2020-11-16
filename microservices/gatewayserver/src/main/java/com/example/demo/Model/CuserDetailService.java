package com.example.demo.Model;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class CuserDetailService implements ReactiveUserDetailsService{

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Optional<UserDetails> userinfo = Optional.ofNullable(
            new CuserDetails(webClient.build().get().uri("http://microuser/"+username)
                        .retrieve()
                        .bodyToMono(Cuser.class).block())
                        );
        System.out.println("webclientresult = " + userinfo.get().getUsername());
        return Mono.justOrEmpty(userinfo);
    }
    
}

package com.example.demo.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return webClient.build().get().uri("http://MICROUSER/"+username)
        .exchange()
        .flatMap(response -> {
            if(response.statusCode().value() == HttpStatus.OK.value()){
                return response.bodyToMono(Cuser.class).flatMap(data ->{
                    System.out.println(data.toString());
                    return Mono.just(new CuserDetails(data));
                });
            }else{
                return Mono.empty();
            }
        });
    }
    
}

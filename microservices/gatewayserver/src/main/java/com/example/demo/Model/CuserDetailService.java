package com.example.demo.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CuserDetailService implements UserDetailsService{

    @Autowired
    private WebClient.Builder webClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Cuser userinfo = webClient.build().get().uri("http://microuser/"+username)
                        .retrieve()
                        .bodyToMono(Cuser.class)
                        .block();
        return new CuserDetails(userinfo);
    }
    
}

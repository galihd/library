package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@EnableWebFluxSecurity
public class SecurityConfig{

    @Autowired
    ReactiveUserDetailsService myUserDetailsService;

    @Bean
    public SecurityWebFilterChain webFilterChain (ServerHttpSecurity http){
        http
        .csrf().disable()
        .authorizeExchange(ex ->
        ex
            .pathMatchers("/","/login").permitAll()
            .pathMatchers("/user/**","/eureka/**").permitAll()
            // .pathMatchers("/user/**").hasAnyAuthority("member","admin")
            .pathMatchers("/dompet/**").hasAnyAuthority("member","admin")
            .pathMatchers("/buku/**").hasAnyAuthority("member","admin")
            .pathMatchers("/transaksi/**").hasAnyAuthority("member","admin")
        .anyExchange()
        .authenticated());
        
        return http.build();
    }

    @Bean
    PasswordEncoder BcryptEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean(name = "dbauth")
    @Primary
    ReactiveAuthenticationManager authenticationManager(){
        System.out.println("this is login");
        UserDetailsRepositoryReactiveAuthenticationManager userDetailsRepository = 
        new UserDetailsRepositoryReactiveAuthenticationManager(myUserDetailsService);

        userDetailsRepository.setPasswordEncoder(BcryptEncoder());
        return userDetailsRepository;
    }

    // @Bean(name = "jwtauth")
    // ReactiveAuthenticationManager jwtauthenticationManager(){
    //     System.out.println("this is jwt");
    //     UserDetailsRepositoryReactiveAuthenticationManager userDetailsRepository = 
    //     new UserDetailsRepositoryReactiveAuthenticationManager(new ReactiveUserDetailsService(){
    //         @Override
    //         public Mono<UserDetails> findByUsername(String username) {
    //             return Mono.just(new User("adam", BcryptEncoder().encode("adam"), true, true, true, true, Arrays.asList(
    //                 new SimpleGrantedAuthority("admin"))
    //             ));
    //         }
    //     });

    //     userDetailsRepository.setPasswordEncoder(BcryptEncoder());
    //     return userDetailsRepository;
    // }

    /*ReactiveAuthenticationManagerResolver<ServerWebExchange> resolver(){
        return ((exchange)->{
            if(
                exchange.getRequest().getPath().
                subPath(0)
                .value()
                .equalsIgnoreCase("/login")
                )
            {
                System.out.println("if result = " + exchange.getRequest().getPath().
                subPath(0)
                .value()
                .equalsIgnoreCase("/login"));
                System.out.println("request path = " + exchange.getRequest().getPath().
                subPath(0));
                return Mono.just(authenticationManager());
            }

            return Mono.just(jwtauthenticationManager());
        });
    }*/
}

   
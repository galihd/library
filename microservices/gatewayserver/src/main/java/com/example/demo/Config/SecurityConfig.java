package com.example.demo.Config;

import com.example.demo.Model.CuserDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CuserDetailService myUserDetailsService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

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
        .and().oauth2Login()
        .and()
        .oauth2ResourceServer().jwt()
        );
        return http.build();
    }

    @Bean
    PasswordEncoder BcryptEncoder(){
        return new BCryptPasswordEncoder(10);
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(BcryptEncoder());
    }
}

   
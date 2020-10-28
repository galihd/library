package com.example.demo.Config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class Corsconfig {
    @Bean
    CorsWebFilter corsWebFilter(){
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        corsConfiguration.setMaxAge(5000L);
        corsConfiguration.setAllowedMethods(Arrays.asList("POST","PUT","DELETE"));
        corsConfiguration.addAllowedHeader("*");

        final CorsConfiguration bukuConfiguration = new CorsConfiguration();
        bukuConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        bukuConfiguration.setMaxAge(10000L);
        bukuConfiguration.setAllowedMethods(Arrays.asList("PUT,POST,GET"));
        bukuConfiguration.addAllowedHeader("*");

        final CorsConfiguration transConfiguration = new CorsConfiguration();
        transConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        transConfiguration.setMaxAge(5000L);
        transConfiguration.setAllowedMethods(Arrays.asList("POST,GET"));
        transConfiguration.addAllowedHeader("*");

        final CorsConfiguration dompetConfiguration = new CorsConfiguration();
        dompetConfiguration.setAllowedOrigins(Collections.singletonList("*"));
        dompetConfiguration.setAllowedMethods(Arrays.asList("GET","UPDATE"));
        dompetConfiguration.setMaxAge(5000L);
        dompetConfiguration.addAllowedHeader(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/user/**", corsConfiguration);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/buku/**", bukuConfiguration);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/trans/**", transConfiguration);
        // urlBasedCorsConfigurationSource.registerCorsConfiguration("/dompet/**", dompetConfiguration);
        return new CorsWebFilter(urlBasedCorsConfigurationSource);
        }
    
}

package com.example.demo.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

public class Authfilter extends AbstractGatewayFilterFactory<Authfilter.Config> {

    public static class Config {

    }

    @Override
    public GatewayFilter apply(Config config) {
        // TODO Auto-generated method stub
        return null;
    }
}

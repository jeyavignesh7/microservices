package com.jv.demo.trade.api.gateway.config;


import com.jv.demo.trade.backend.service.authapi.config.JwtAuthenticationFilter;
import com.jv.demo.trade.backend.service.authapi.config.RouteValidator;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Configuration
public class MyLoadBalancerConfig {

    @Bean
    public RouteValidator routeValidator(){
        return new RouteValidator();
    }
    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    @Lazy
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

}
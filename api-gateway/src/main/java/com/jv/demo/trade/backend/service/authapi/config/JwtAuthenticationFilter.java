package com.jv.demo.trade.backend.service.authapi.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.Map;

@Component
@CommonsLog
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;
    @Autowired
    @Qualifier(value="loadBalancedWebClientBuilder")
    private WebClient.Builder loadBalancedWebClientBuilder;

    private WebClient webClient;

    public JwtAuthenticationFilter() {
        super(Config.class);
    }

    @PostConstruct
    public void init(){
        log.error("######################### JwtAuthenticationFilter.init()");
        System.out.println("######################### + JwtAuthenticationFilter.init()");
        this.webClient = loadBalancedWebClientBuilder
                .baseUrl("http://AUTH-API-SERVICE") // logical service name for load balancing
                .build();
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.error("#########################" + exchange.getRequest().getURI().getPath());
            System.out.println("#########################" + exchange.getRequest().getURI().getPath());
            if (validator.isSecured.test(exchange.getRequest())) {
                String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
                System.out.println("#########################" + authHeader);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    String token = authHeader.substring(7); // remove 'Bearer ' prefix

                    return validateToken(token)
                            .flatMap(userId -> proceedWithUserId(userId, exchange, chain))
                            .onErrorResume(e -> handleAuthenticationError(exchange, e));
                } else { // No token present
                    return handleAuthenticationError(exchange, new RuntimeException("Missing Authorization Header"));
                }
            }

            return chain.filter(exchange);

        };
    }

    private Mono<String> validateToken(String token) {
        return Mono.justOrEmpty(
            webClient.post()
                .uri("/auth-api-service/auth/validate")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(Collections.singletonMap("token", token))
                .retrieve()
                .toEntity(String.class)
                .toString());
        /*
        return webClient.post()
            .uri("/auth-api-service/auth/validate")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .bodyValue(Collections.singletonMap("token", token))
            .retrieve()
            .bodyToMono(Map.class)
            .map(response -> response.get("userId").toString());
         */
    }

    private Mono<Void> proceedWithUserId(String userId, ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest mutatedRequest = exchange.getRequest()
                .mutate()
                .header("X-USER-ID", userId)
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    private Mono<Void> handleAuthenticationError(ServerWebExchange exchange, Throwable e) {
        log.error("Authentication failed: {}", e);
        e.printStackTrace();
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

    public static class Config {
        // Configuration properties for your filter if needed
    }
}

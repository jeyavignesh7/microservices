package com.jv.demo.tradenet.frontend.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FrontendPresentationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendPresentationApplication.class, args);
    }
/*
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("service1", r -> r.path("/service1/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://SERVICE-NAME1"))
				.route("service2", r -> r.path("/service2/**")
						.filters(f -> f.stripPrefix(1))
						.uri("lb://SERVICE-NAME2"))
				.build();
	}

 */
}

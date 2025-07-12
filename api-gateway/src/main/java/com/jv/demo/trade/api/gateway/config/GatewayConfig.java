package com.jv.demo.trade.api.gateway.config;


import com.jv.demo.trade.backend.service.authapi.config.JwtAuthenticationFilter;
import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import reactor.netty.http.client.HttpClient;


@Configuration
@EnableDiscoveryClient
@CommonsLog
public class GatewayConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public GatewayConfig(JwtAuthenticationFilter jwtAuthenticationFilter){
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        log.error(builder.routes().toString());
        System.out.println("###############" + builder.routes().toString());
        return builder.routes()
                .route("FRONTEND-SERVICE", r -> r.path("/frontend-service/excrate/**")
                        .uri("lb://FRONTEND-SERVICE"))
                .route("AUTH-API-SERVICE", r -> r.path("/auth-api-service/auth/**")
                        .uri("lb://AUTH-API-SERVICE"))
                .route("HS-SERVICE", r -> r.path("/hs-service/hs/**")
                        .uri("lb://HS-SERVICE"))
             //   .route("LOCATION-SERVICE", r -> r.path("/location-service/country/**")
             //           .uri("lb://LOCATION-SERVICE"))
                .route("LOCATION-SERVICE_DEF", r -> r.path("/country")
                        .filters(f -> f.rewritePath("/country", "/location-service/country"))
                        .uri("lb://LOCATION-SERVICE"))
                .route("LOCATION-SERVICE", r -> r.path("/country/**")
                        .filters(f -> f.rewritePath("/country/(?<segment>.*)", "/location-service/country/${segment}"))
                        .uri("lb://LOCATION-SERVICE"))
            //    .route("COMPANY-SERVICE", r -> r.path("/company-service/company/**")
            //            .uri("lb://COMPANY-SERVICE"))
                .route("COMPANY-SERVICE_DEF", r -> r.path("/company")
                        .filters(f -> f.rewritePath("/company", "/company-service/company"))
                        .uri("lb://COMPANY-SERVICE"))
                .route("COMPANY-SERVICE", r -> r.path("/company/**")
                            .filters(f -> {
                                f.rewritePath("/company/(?<segment>.*)", "/company-service/company/${segment}");
                                return f.filter(jwtAuthenticationFilter.apply(new JwtAuthenticationFilter.Config()));
                            })
                            .uri("lb://COMPANY-SERVICE"))

                // Need to try combining all possible paths to rewrite
                /*
                .route("LOCATION-SERVICE", r -> r.path("/country", "/country/**")
                        .filters(f -> f.rewritePath("/country/(?<segment>.*)|/country()", "/location-service/country/${segment}"))
                        .uri("lb://LOCATION-SERVICE"))

                 */


                .build();
    }

    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }

/*
    @Bean
    WebFluxProperties webFluxProperties(){
        return new WebFluxProperties();
    }

 */
}

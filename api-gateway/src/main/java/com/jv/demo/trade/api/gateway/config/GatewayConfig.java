package com.jv.demo.trade.api.gateway.config;

import io.netty.resolver.DefaultAddressResolverGroup;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.client.HttpClient;


@Configuration
@EnableDiscoveryClient
@CommonsLog
public class GatewayConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        log.error(builder.routes().toString());
        return builder.routes()
                .route("frontend-service", r -> r.path("/frontend-service/excrate/**")
                        .uri("lb://FRONTEND-SERVICE"))
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
                        .filters(f -> f.rewritePath("/company/(?<segment>.*)", "/company-service/company/${segment}"))
                        .uri("lb://COMPANY-SERVICE"))

                // Need to try combining all possible paths to rewrite
                /*
                .route("LOCATION-SERVICE", r -> r.path("/country", "/country/**")
                        .filters(f -> f.rewritePath("/country/(?<segment>.*)|/country()", "/location-service/country/${segment}"))
                        .uri("lb://LOCATION-SERVICE"))

                 */


                .build();
    }

    /*
    @Bean
    public HttpClient httpClient() {
        return HttpClient.create().resolver(DefaultAddressResolverGroup.INSTANCE);
    }
    */
}

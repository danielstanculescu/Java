package com.example.gateway_spring_cloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig {
    
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder.routes()
                // http://localhost:8082/demo/resource
                .route(p -> p.path("/resource/**")
                        .filters(path -> path.rewritePath("/resource/", "/"))
                        .uri("http://localhost:8082"))
                .route(p -> p.path("/**").uri("http://localhost:8081"))

                .build();
    }

}

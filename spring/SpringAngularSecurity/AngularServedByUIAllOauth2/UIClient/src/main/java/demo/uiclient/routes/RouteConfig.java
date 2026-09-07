package demo.uiclient.routes;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {


        return builder.routes()
                // http://127.0.0.1:8080/resource/hello -> http://127.0.0.1:8090/hello
                .route(
                        r -> r
                                .path("/resource/**")
                                .filters(
                                        f -> f
                                                .rewritePath("/resource/", "/")
                                                .tokenRelay()
                                )
                                .uri("http://127.0.0.1:8090")
                )
                // http://127.0.0.1:8080/data/api -> http://127.0.0.1:8080/api
                .route(
                        r -> r
                                .path("/data/**")
                                .filters(
                                        f -> f
                                                .rewritePath("/data/", "/")
                                                .tokenRelay()

                                )
                                .uri("http://127.0.0.1:8080")
                )
                // http://127.0.0.1:8080/data2 -> http://127.0.0.1:8090/api/data
                .route(
                        r -> r
                                .path("/data2/**")
                                .filters(
                                        f -> f
                                                .rewritePath("/data2", "/api/data")
                                                .tokenRelay()

                                )
                                .uri("http://127.0.0.1:8090")
                )
                .build();
    }


}
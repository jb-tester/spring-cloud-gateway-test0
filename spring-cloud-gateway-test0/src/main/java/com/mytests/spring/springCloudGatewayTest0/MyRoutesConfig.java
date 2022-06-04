package com.mytests.spring.springCloudGatewayTest0;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

/**
 * *
 * <p>Created by irina on 6/2/2022.</p>
 * <p>Project: spring-cloud-gateway-test0</p>
 * *
 */
@Configuration
public class MyRoutesConfig {

    @Value("${my.server.url}")
    String myurl;

    @Bean
    public RouteLocator testRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("one", p -> p
                        .path("/test1","/")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(myurl))
                .route("two", p -> p
                        .method("GET","POST").and().path("/test3")
                        .filters(f -> f.redirect(302,"/redirected"))
                        .uri("no://op"))
                .route("three", p -> p
                        .path("/test5/{pv}").or().path("/test6/{pv}")
                        .filters(f -> f.setPath("/test56/{pv}"))
                        .uri(myurl))
                .route("four", p -> p
                        .path("/test4")
                        .filters(f -> f.prefixPath("/myprefix"))
                        .uri(myurl))
                .route("five", p -> p
                        .method("GET")
                        .and().path("/test7")
                        .and().query("param1")
                        .uri(myurl)
                      )
                .route(p -> p
                        .host("foo.com")
                        .filters(f -> f.circuitBreaker(config -> config
                                .setName("mycmd")
                                .setFallbackUri("forward:/fallback")
                        ))
                        .uri(myurl))
                .build();
    }
}

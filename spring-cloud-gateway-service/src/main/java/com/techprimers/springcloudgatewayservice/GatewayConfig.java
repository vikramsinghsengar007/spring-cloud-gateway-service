package com.techprimers.springcloudgatewayservice;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableHystrix
@Configuration
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class GatewayConfig {
//hardcoding the uro otherwise go for yml config
//https://www.youtube.com/watch?v=bRBgVMngHcQ&ab_channel=TechPrimers
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()

                .route(p -> p
                        .path("/all")
                        .filters(f ->
                                f.addRequestHeader("x-rapidapi-host", "restcountries-v1.p.rapidapi.com")
                                        .addRequestHeader("x-rapidapi-key", "42ed469d51mshb48f71ba42f13ccp1d9cccjsna7812c29ebe3")
                                        .hystrix(config -> config.setName("countries-service")
                                                .setFallbackUri("forward:/countriesfallback"))
                        )
                        .uri("https://restcountries-v1.p.rapidapi.com")
                )
                .route(p -> p
                        .path("/v1/joke")
                        .filters(f ->
                                f.addRequestHeader("x-rapidapi-host", "joke3.p.rapidapi.com")
                                        .addRequestHeader("x-rapidapi-key", "42ed469d51mshb48f71ba42f13ccp1d9cccjsna7812c29ebe3")
                                        .hystrix(config -> config.setName("joke-service")
                                                .setFallbackUri("forward:/jokefallback"))
                        )
                        .uri("https://joke3.p.rapidapi.com")
                )
                .build();
    }
}

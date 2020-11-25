package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class ZuulApplication {

    /**
     * 配置路由规则 ,可在代码中 或在 yml 中
     */
/*    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(
                        r -> r.path("/**").uri("lb://test-a"))
                .route(
                        r -> r.path("/test").uri("lb://test-b"))
                .build();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}

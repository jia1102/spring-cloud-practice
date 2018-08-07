package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ApiGatewayApp {
    public static void main( String[] args ) {
        SpringApplication.run(ApiGatewayApp.class,args);
    }
}

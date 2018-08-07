package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroProviderDept8001App {
    public static void main( String[] args ) {
        SpringApplication.run(MicroProviderDept8001App.class,args);
    }
}

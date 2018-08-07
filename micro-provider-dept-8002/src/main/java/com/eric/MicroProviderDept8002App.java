package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MicroProviderDept8002App {
    public static void main( String[] args ) {
        SpringApplication.run(MicroProviderDept8002App.class,args);
    }
}

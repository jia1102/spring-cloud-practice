package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ConfigClient7700App {
    public static void main( String[] args ) {
        SpringApplication.run(ConfigClient7700App.class, args);
    }
}

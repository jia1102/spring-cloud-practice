package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DeptConsumer9100App {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer9100App.class, args);
    }
}

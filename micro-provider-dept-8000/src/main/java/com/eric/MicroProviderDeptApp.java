package com.eric;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;


@SpringBootApplication
@EnableCircuitBreaker
@MapperScan("com.eric.dao")
public class MicroProviderDeptApp {
    public static void main( String[] args ) {
        SpringApplication.run(MicroProviderDeptApp.class,args);
    }
}

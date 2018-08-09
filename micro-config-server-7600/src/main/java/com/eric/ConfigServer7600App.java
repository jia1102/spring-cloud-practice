package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer7600App {
    public static void main( String[] args ) {
        SpringApplication.run(ConfigServer7600App.class, args);
    }
}

package com.eric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

//@ImportResource(value = {"classpath:beans.xml"})
@SpringBootApplication
public class MicroSpringbootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroSpringbootDemoApplication.class, args);

//        new SpringApplicationBuilder(MicroSpringbootDemoApplication.class).properties("spring.config.name=application,application_data").run(args);
    }

/*    @Bean
    public ViewResolver myViewResolver(){
        return new MyViewResolver();
    }

    private static class MyViewResolver implements ViewResolver{

        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }*/
}

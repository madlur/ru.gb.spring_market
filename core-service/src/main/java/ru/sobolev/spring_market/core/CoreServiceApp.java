package ru.sobolev.spring_market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("secrets.properties")
public class CoreServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(CoreServiceApp.class, args);
    }

}

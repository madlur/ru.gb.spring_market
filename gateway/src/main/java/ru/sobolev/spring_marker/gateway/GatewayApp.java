package ru.sobolev.spring_marker.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// ToDo https://cloud.spring.io/spring-cloud-gateway/reference/html/

@SpringBootApplication
public class GatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class,args);
    }
}
package com.mechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mechat")
public class Server {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Server.class);
        app.setBanner(new CustomBanner());
        app.run(args);
    }
}

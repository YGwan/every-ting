package com.everyTing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application, application-core");
        SpringApplication.run(TeamApplication.class, args);
    }
}
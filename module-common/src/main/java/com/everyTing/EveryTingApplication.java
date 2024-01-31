package com.everyTing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class EveryTingApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application, application-core");
        SpringApplication.run(EveryTingApplication.class, args);
    }
}

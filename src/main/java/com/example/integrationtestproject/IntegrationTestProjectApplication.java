package com.example.integrationtestproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegrationTestProjectApplication {

    public static void main(String[] args) {

        Class<?>[] classes = new Class<?>[] {IntegrationTestProjectApplication.class,
                IntegrationTestProjectApplication.class
        };

        SpringApplication.run(classes, args);
    }

}

package com.example.integrationtestproject;

import com.example.integrationtestproject.tcp.tutorial.t2transformer.IntegrationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegrationTestProjectApplication {

    public static void main(String[] args) {

        Class<?>[] classes = new Class<?>[] {IntegrationTestProjectApplication.class,
//                IntegrationConfig.class,
                IntegrationTestProjectApplication.class
        };

        SpringApplication.run(classes, args);
    }

}

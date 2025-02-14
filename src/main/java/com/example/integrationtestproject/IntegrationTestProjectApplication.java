package com.example.integrationtestproject;

//import com.example.integrationtestproject.tcp.my.TestIntegrationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IntegrationTestProjectApplication {

    public static void main(String[] args) {

//        Class<?>[] classes = new Class<?>[] {IntegrationTestProjectApplication.class,
//                TestIntegrationConfig.class
//        };

        SpringApplication.run(IntegrationTestProjectApplication.class, args);
    }

}

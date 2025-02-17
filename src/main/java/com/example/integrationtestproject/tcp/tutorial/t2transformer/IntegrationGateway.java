package com.example.integrationtestproject.tcp.tutorial.t2transformer;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IntegrationGateway {

    @Gateway(requestChannel = "integration.gateway.channel")
    String sendMessage(String message);

    @Gateway(requestChannel = "integration.student.gateway.channel")
    String processStudentDetails(Student student);
}

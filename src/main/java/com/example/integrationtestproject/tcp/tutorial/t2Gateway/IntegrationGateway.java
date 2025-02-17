package com.example.integrationtestproject.tcp.tutorial.t2Gateway;

import com.example.integrationtestproject.tcp.tutorial.t3Transformer.Student;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface IntegrationGateway {

    //t2//begin//
    @Gateway(requestChannel = "integration.gateway.channel")
    String sendMessage(String message);
    //t2//end//

    //t//begin//
    @Gateway(requestChannel = "integration.student.gateway.channel")
    String processStudentDetails(Student student);
    //t//begin//

    //t5//begin//
    @Gateway(requestChannel = "router.channel")
    <T> void process(T object);
    //t5//begin//

    //t6//begin//
    @Gateway(requestChannel = "router2.channel")
    void process2(Student student);
    //t6//begin//
}

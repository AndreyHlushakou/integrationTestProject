package com.example.integrationtestproject.tcp.tutorial.t5Router;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class StudentService5 {

    @ServiceActivator(inputChannel = "student.channel")
    public Message<?> receiveMessage(Message<?> message) throws MessagingException {
        System.out.println("###############student.channel#################");
        System.out.println(message);
        System.out.println("################################");
        System.out.println(message.getPayload());
        return message;
    }

}

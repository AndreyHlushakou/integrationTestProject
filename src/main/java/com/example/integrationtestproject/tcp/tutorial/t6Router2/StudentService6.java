package com.example.integrationtestproject.tcp.tutorial.t6Router2;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Component
public class StudentService6 {

    @ServiceActivator(inputChannel = "student.channel1")
    public Message<?> receiveMessage1(Message<?> message) throws MessagingException {
        System.out.println("###############student.channel1#################");
        System.out.println(message);
        System.out.println("################################");
        System.out.println(message.getPayload());
        return message;
    }

    @ServiceActivator(inputChannel = "student.channel2")
    public Message<?> receiveMessage2(Message<?> message) throws MessagingException {
        System.out.println("###############student.channel2#################");
        System.out.println(message);
        System.out.println("################################");
        System.out.println(message.getPayload());
        return message;
    }

}

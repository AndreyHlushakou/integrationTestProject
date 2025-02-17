//package com.example.integrationtestproject.tcp.examples.tutorial.t5Router;
//
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessagingException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AddressService5 {
//
//    @ServiceActivator(inputChannel = "address.channel")
//    public Message<?> reciveMessage(Message<?> message) throws MessagingException {
//        System.out.println("###############address.channel#################");
//        System.out.println(message);
//        System.out.println("################################");
//        System.out.println(message.getPayload());
//        return message;
//    }
//}

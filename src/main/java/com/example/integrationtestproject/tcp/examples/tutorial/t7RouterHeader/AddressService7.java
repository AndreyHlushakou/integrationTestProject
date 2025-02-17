//package com.example.integrationtestproject.tcp.examples.tutorial.t7RouterHeader;
//
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessagingException;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AddressService7 {
//
//    @ServiceActivator(inputChannel = "address.channel")
//    public Message<?> receiveMessage(Message<?> message) throws MessagingException {
//        System.out.println("###############address.channel#################");
//        System.out.println(message);
//        System.out.println("################################");
//        System.out.println(message.getPayload());
//        return message;
//    }
//
//}

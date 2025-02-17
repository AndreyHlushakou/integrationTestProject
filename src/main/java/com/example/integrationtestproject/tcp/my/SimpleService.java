//package com.example.integrationtestproject.tcp.my;
//
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessagingException;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SimpleService {
//
//    @ServiceActivator(inputChannel = "integration.gateway.channel")
//    public void anotherMessage(Message<byte[]> message) throws MessagingException {
//        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
//
//        byte[] from = message.getPayload();
//        System.out.println(bytesToHex(from));
//
////        MessageBuilder.fromMessage(message);
////        Message<String> newMessage = MessageBuilder
////                .withPayload("Welcome, " + message.getPayload() + " to Spring Integration")
////                .build();
////        replyChannel.send(newMessage);
//    }
//
//    private static String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02X ", b));
//        }
//        return sb.toString().trim();
//    }
//
//}

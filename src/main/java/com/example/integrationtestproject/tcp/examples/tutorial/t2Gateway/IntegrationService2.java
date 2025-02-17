//package com.example.integrationtestproject.tcp.examples.tutorial.t2Gateway;
//
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessagingException;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class IntegrationService2 {
//
////    @ServiceActivator(inputChannel = "integration.gateway.channel", outputChannel = "integration.gateway.channel.serviceactivator")
////    public Message<String> reciveMessage(Message<String> message) throws MessagingException {
////        MessageBuilder.fromMessage(message);
////        Message<String> newMessage = MessageBuilder
////                .withPayload(message.getPayload() + " modified in integration.gateway.channel, ")
////                .build();
////        return newMessage;
////    }
////
////    @ServiceActivator(inputChannel = "integration.gateway.channel.serviceactivator")
////    public void anotherMessage(Message<String> message) throws MessagingException {
////        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
////        MessageBuilder.fromMessage(message);
////        Message<String> newMessage = MessageBuilder
////                .withPayload(message.getPayload() + " and received into integration.gateway.channel.serviceactivator")
////                .build();
////        replyChannel.send(newMessage);
////    }
//
//    @ServiceActivator(inputChannel = "integration.gateway.channel")
//    public void anotherMessage(Message<String> message) throws MessagingException {
//        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
//        MessageBuilder.fromMessage(message);
//        Message<String> newMessage = MessageBuilder
//                .withPayload("Welcome, " + message.getPayload() + " to Spring Integration")
//                .build();
//        replyChannel.send(newMessage);
//    }
//
//}

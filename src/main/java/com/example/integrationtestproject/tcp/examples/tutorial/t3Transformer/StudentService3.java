//package com.example.integrationtestproject.tcp.examples.tutorial.t3Transformer;
//
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessagingException;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class StudentService3 {
//
//    @ServiceActivator(inputChannel = "integration.student.objectToJson.channel", outputChannel = "integration.student.jsonToObject.fromTransformer.channel")
//    public Message<?> receiveMessage(Message<?> message) throws MessagingException {
//        System.out.println("################################");
//        System.out.println(message);
//        System.out.println("################################");
//        System.out.println("Object to Json - " + message.getPayload());
//        return message;
//    }
//
//    @ServiceActivator(inputChannel = "integration.student.jsonToObject.fromTransformer.channel")
//    public void processJsonToObject(Message<Student> message) throws MessagingException {
//        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
//        MessageBuilder.fromMessage(message);
//        System.out.println("################################");
//        System.out.println("Json to Object - " + message.getPayload());
//        Student student = message.getPayload();
//        Message<?> newMessage = MessageBuilder.withPayload(student.toString()).build();
//        replyChannel.send(newMessage);
//    }
//
//}

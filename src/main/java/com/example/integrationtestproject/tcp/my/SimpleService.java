package com.example.integrationtestproject.tcp.my;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class SimpleService {

    @ServiceActivator(inputChannel = "inboundChannel", outputChannel = "outboundChannel")
    public Message<byte[]> processMessage(Message<byte[]> message) throws MessagingException {

        handlerMessage(message);

        byte[] response = new byte[]{0x11, 0x22, 0x33};
        Message<byte[]> newMessage = MessageBuilder.withPayload(response).build();
        return newMessage;
    }

    private static boolean handlerMessage(Message<byte[]> message) {
        byte[] byteMessage = message.getPayload();
        System.out.println("################################");
        System.out.println("Object - " + bytesToHex(byteMessage));
        System.out.println("################################");

        return byteMessage[0] == 0x11;
    }

    static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }

}

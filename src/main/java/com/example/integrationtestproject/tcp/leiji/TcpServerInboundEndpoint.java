package com.example.integrationtestproject.tcp.leiji;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.IpHeaders;
import org.springframework.messaging.Message;

import static com.example.integrationtestproject.tcp.leiji.TcpServerConfig.MESSAGE_CHANNEL;

@MessageEndpoint
@Slf4j
@RequiredArgsConstructor
public class TcpServerInboundEndpoint {


    @ServiceActivator(inputChannel = MESSAGE_CHANNEL, requiresReply = "true")
    public byte[] onMessage(Message<byte[]> message) {
        log.info("received message with connection id {}", message.getHeaders().get(IpHeaders.CONNECTION_ID));

        byte[] bytePayload = message.getPayload();

        String payloadString = bytesToHex(bytePayload);
        log.info("Message:{}", payloadString);

        if (bytePayload[0] == 0x7E) {
            return new byte[]{0x11, 0x22, 0x33};
        }

        return new byte[]{0x55, 0x66, 0x77};
    }

     // Вспомогательный метод для преобразования массива байт в HEX-строку
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }

}



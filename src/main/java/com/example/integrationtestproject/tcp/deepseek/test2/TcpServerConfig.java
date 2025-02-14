//package com.example.integrationtestproject.tcp.deepseek.test2;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.ip.tcp.TcpInboundGateway;
//import org.springframework.integration.ip.tcp.connection.TcpConnectionCloseEvent;
//import org.springframework.integration.ip.tcp.connection.TcpConnectionOpenEvent;
//import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
//import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHandler;
//
//@Configuration
//public class TcpServerConfig {
//
//    @Value("${tcpServer.port}")
//    private int port;
//
//    @Bean
//    public TcpNioServerConnectionFactory serverConnectionFactory() {
//        TcpNioServerConnectionFactory factory = new TcpNioServerConnectionFactory(port);
//        factory.setSerializer(new ByteArrayRawSerializer());
//        factory.setDeserializer(new ByteArrayRawSerializer());
//        return factory;
//    }
//
//    @Bean
//    public TcpInboundGateway tcpInboundGateway() {
//        TcpInboundGateway gateway = new TcpInboundGateway();
//        gateway.setConnectionFactory(serverConnectionFactory());
//        gateway.setRequestChannel(tcpInboundChannel());
//        return gateway;
//    }
//
//    @Bean
//    public MessageChannel tcpInboundChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "tcpInboundChannel")
//    public MessageHandler handler() {
//        return message -> {
//            byte[] payload = (byte[]) message.getPayload();
//            String connectionId = (String) message.getHeaders().get("ip_connectionId");
//            System.out.println("Received from " + connectionId + ": " + bytesToHex(payload));
//
//            // Обработка специального массива байт
//            if (isSpecialMessage(payload)) {
//                byte[] response = new byte[]{(byte) 0xFF, 0x11, 0x11, 0x11, 0x11, (byte) 0xFF};
//                System.out.println("Sending special response to " + connectionId);
//                // Отправка ответа
//                // В реальном приложении нужно использовать TcpOutboundGateway или другой механизм для отправки ответа
//            }
//        };
//    }
//
//        // Обработчик событий подключения/отключения
//    @EventListener
//    public void handleTcpConnectionOpen(TcpConnectionOpenEvent event) {
//        System.out.println("Клиент подключился: " + event.getConnectionId());
//    }
//
//    @EventListener
//    public void handleTcpConnectionClose(TcpConnectionCloseEvent event) {
//        System.out.println("Клиент отключился: " + event.getConnectionId());
//    }
//
//    private boolean isSpecialMessage(byte[] payload) {
//        byte[] specialMessage = new byte[]{(byte) 0xFF, (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0xFF};
//        return java.util.Arrays.equals(payload, specialMessage);
//    }
//
//    private String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02X ", b));
//        }
//        return sb.toString();
//    }
//}

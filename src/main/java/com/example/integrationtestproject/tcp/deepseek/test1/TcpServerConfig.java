//package com.example.integrationtestproject.tcp.deepseek.test1;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.channel.PublishSubscribeChannel;
//import org.springframework.integration.ip.tcp.TcpInboundGateway;
//import org.springframework.integration.ip.tcp.connection.*;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//
//@Configuration
//public class TcpServerConfig {
//
//    // Порт, на котором будет работать TCP-сервер
//    @Value("${tcpServer.port}")
//    private int TCP_PORT;
//
//    // Канал для входящих сообщений
//    @Bean
//    public MessageChannel tcpInputChannel() {
//        return new DirectChannel();
//    }
//
//    // Канал для уведомлений о подключении/отключении
//    @Bean
//    public MessageChannel eventChannel() {
//        return new PublishSubscribeChannel();
//    }
//
//    // TCP-сервер
//    @Bean
//    public AbstractServerConnectionFactory serverConnectionFactory() {
//        return new TcpNetServerConnectionFactory(TCP_PORT);
//    }
//
//    // TCP-шлюз для обработки входящих сообщений
//    @Bean
//    public TcpInboundGateway tcpInboundGateway() {
//        TcpInboundGateway gateway = new TcpInboundGateway();
//        gateway.setConnectionFactory(serverConnectionFactory());
//        gateway.setRequestChannel(tcpInputChannel());
//        return gateway;
//    }
//
//    // Обработчик входящих сообщений
//    @ServiceActivator(inputChannel = "tcpInputChannel")
//    public byte[] handleMessage(Message<byte[]> message) {
//        byte[] payload = message.getPayload();
//        System.out.println("Received: " + bytesToHex(payload));
//
//        // Проверка на специальный массив байт (FF 99 99 99 99 FF)
//        if (isSpecialMessage(payload)) {
//            System.out.println("Special message received. Sending response...");
//            return new byte[]{(byte) 0xFF, 0x11, 0x11, 0x11, 0x11, (byte) 0xFF};
//        }
//
//        // Если это не специальный массив, возвращаем null (ничего не отправляем)
//        return null;
//    }
//
//    // Обработчик событий подключения/отключения
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
//    // Вспомогательный метод для проверки специального массива байт
//    private boolean isSpecialMessage(byte[] payload) {
//        byte[] specialMessage = {(byte) 0xFF, (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0x99, (byte) 0xFF};
//        return java.util.Arrays.equals(payload, specialMessage);
//    }
//
//    // Вспомогательный метод для преобразования массива байт в HEX-строку
//    private String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02X ", b));
//        }
//        return sb.toString().trim();
//    }
//}
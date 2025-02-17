//package com.example.integrationtestproject.tcp.my.dsl;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
//import org.springframework.integration.ip.tcp.connection.*;
//import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
//import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Configuration
//public class IntegrationConfiguration {
//
//    @Value("${tcpServer.port}")
//    private int port;
//
//    @Bean
//    public MessageChannel inboundChannel() {
//        return new DirectChannel();
//    }
//
//
//    @Bean
//    public AbstractServerConnectionFactory serverConnectionFactory() {
//        AbstractServerConnectionFactory factory = new TcpNetServerConnectionFactory(port);
//        AbstractByteArraySerializer serializer = new ByteArrayRawSerializer();
//        factory.setSerializer(serializer);
//        factory.setDeserializer(serializer);
//        return factory;
//    }
//
//    @Bean
//    public TcpReceivingChannelAdapter tcpReceivingChannelAdapter(
//            AbstractServerConnectionFactory serverFactory,
//            MessageChannel inboundChannel) {
//        TcpReceivingChannelAdapter tcpReceivingChannelAdapter = new TcpReceivingChannelAdapter();
//        tcpReceivingChannelAdapter.setConnectionFactory(serverFactory);
//        tcpReceivingChannelAdapter.setOutputChannel(inboundChannel);
//        return tcpReceivingChannelAdapter;
//    }
//
//    @Bean
//    public IntegrationFlow flowInput(MessageChannel inboundChannel) {
//        return IntegrationFlow.from(inboundChannel)
//                .transform(IntegrationConfiguration::bytesToHex)
//                .handle((s) -> System.out.println(s.getPayload()))
//                .get();
//    }
//
//
////    @Bean
////    public IntegrationFlow flowInput() {
////        return IntegrationFlow.from(inboundChannel())
////                .split(Message.class, this::splitBytes) // Разделяем байты
////                .handle(m -> {
////                    System.out.println("Received: " + bytesToHex((byte[]) m.getPayload())); // Немедленный вывод
////                })
////                .get();
////    }
//
//    private List<byte[]> splitBytes(Message<?> message) {
//        byte[] payload = (byte[]) message.getPayload();
//        List<byte[]> messages = new ArrayList<>();
//        int chunkSize = 4; // Размер одного сообщения
//
//        for (int i = 0; i < payload.length; i += chunkSize) {
//            int end = Math.min(payload.length, i + chunkSize);
//            messages.add(Arrays.copyOfRange(payload, i, end));
//        }
//
//        return messages;
//    }
//
//
//    private static String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02X ", b));
//        }
//        return sb.toString().trim();
//    }
//
//
//    @EventListener
//    public void handleTcpConnectionOpen(TcpConnectionOpenEvent event) {
//        System.out.println("Client connected: " + event.getConnectionId());
//    }
//
//    @EventListener
//    public void handleTcpConnectionClose(TcpConnectionCloseEvent event) {
//        System.out.println("Client disconnected: " + event.getConnectionId());
//    }
//
//    @EventListener
//    public void handleTcpConnectionExceptionEvent(TcpConnectionExceptionEvent event) {
//        System.out.println("Exception: " + event);
//    }
//
//}

//package com.example.integrationtestproject.tcp.chatgpt.test1;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
//import org.springframework.integration.ip.tcp.connection.*;
//import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.support.GenericMessage;
//
//import java.util.concurrent.Executors;
//
//@Configuration
//public class IntegrationConfig {
//
//    @Value("${tcpServer.port}")
//    private int PORT;
//
//    //получить
//    @Bean
//    public MessageChannel inboundChannel() {
//        return new DirectChannel(); //new DirectChannel()
//    }
//
////    //отправить
////    @Bean
////    public MessageChannel outboundChannel() {
////        return new DirectChannel();
////    }
//
//    //подключения
//    @Bean
//    public TcpNetServerConnectionFactory connectionFactory() {
//        TcpNetServerConnectionFactory factory = new TcpNetServerConnectionFactory(PORT);
//        factory.setSerializer(new ByteArrayRawSerializer());
//        factory.setDeserializer(new ByteArrayRawSerializer());
//        factory.setTaskExecutor(Executors.newCachedThreadPool());
//        return factory;
//    }
//
//    //получить
//    @Bean
//    public TcpReceivingChannelAdapter receiving(
//            TcpNetServerConnectionFactory connectionFactory,
//            MessageChannel inboundChannel) {
//        TcpReceivingChannelAdapter receivingMessageAdapter = new TcpReceivingChannelAdapter();
//        receivingMessageAdapter.setConnectionFactory(connectionFactory);
//        receivingMessageAdapter.setOutputChannel(inboundChannel);
//        return receivingMessageAdapter;
//    }
//
////    //отправить
////    @Bean
////    public TcpSendingMessageHandler heartbeatSendingMessageHandler(
////            TcpNetServerConnectionFactory connectionFactory) {
////        TcpSendingMessageHandler sendingMessageHandler = new TcpSendingMessageHandler();
////        sendingMessageHandler.setConnectionFactory(connectionFactory);
////        return sendingMessageHandler;
////    }
//
//
////    @Bean
////    public TcpInboundGateway tcpInboundGateway() {
////        TcpInboundGateway inboundGateway = new TcpInboundGateway();
////        inboundGateway.setConnectionFactory(connectionFactory());
////        inboundGateway.setRequestChannel(inboundChannel());
////        return inboundGateway;
////    }
//
//
//
//
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
//    @ServiceActivator(inputChannel = "inboundChannel")
//    public void handleMessage(Message<byte[]> message) {
//        byte[] payload = message.getPayload();
//        String receivedData = bytesToHex(payload);
//        System.out.println("Принятые данные: " + receivedData);
//
//        // Принудительная отправка пустого сообщения для обработки сразу после получения
//        MessageChannel replyChannel = (MessageChannel) message.getHeaders().getReplyChannel();
//        if (replyChannel != null) {
//            replyChannel.send(new GenericMessage<>(new byte[]{(byte) 0xFF, (byte) 0xFF}));
//        }
//    }
//
//    private String bytesToHex(byte[] bytes) {
//        StringBuilder sb = new StringBuilder();
//        for (byte b : bytes) {
//            sb.append(String.format("%02X ", b));
//        }
//        return sb.toString().trim();
//    }
//}
//

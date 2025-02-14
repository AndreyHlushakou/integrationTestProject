//package com.example.integrationtestproject.tcp.my;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.event.EventListener;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.ip.dsl.Tcp;
//import org.springframework.integration.ip.dsl.TcpNioServerConnectionFactorySpec;
//import org.springframework.integration.ip.tcp.TcpInboundGateway;
//import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
//import org.springframework.integration.ip.tcp.connection.*;
//import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
//import org.springframework.messaging.MessageChannel;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//@EnableIntegration
//public class TestIntegrationConfig {
//
//    @Value("${tcpServer.port}")
//    private int port;
//
//    @Bean
//    public MessageChannel inboundChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    TcpNioServerConnectionFactorySpec serverFactory() {
//        return Tcp.nioServer(port);
//    }
//
////    @Bean
////    public AbstractServerConnectionFactory  clientConnectionFactory() {
////        TcpNioServerConnectionFactory tcpNioServerConnectionFactory = new TcpNioServerConnectionFactory(this.port);
////        tcpNioServerConnectionFactory.setUsingDirectBuffers(true);
////        tcpNioServerConnectionFactory.setSingleUse(false);
////        ByteArrayLengthHeaderSerializer serializer = new ByteArrayLengthHeaderSerializer(2);
////        tcpNioServerConnectionFactory.setSerializer(serializer);
////        tcpNioServerConnectionFactory.setDeserializer(serializer);
////        return tcpNioServerConnectionFactory;
////    }
//
//    @Bean
//    public AbstractServerConnectionFactory connectionFactory() {
//        TcpNetServerConnectionFactory factory = new TcpNetServerConnectionFactory(port);
//        ByteArrayRawSerializer rawSerializer = new ByteArrayRawSerializer();
//        factory.setSerializer(rawSerializer);
//        factory.setDeserializer(rawSerializer);
//        return factory;
//    }
//
//    @Bean
//    public TcpReceivingChannelAdapter tcpReceivingChannelAdapter(
//            AbstractServerConnectionFactory connectionFactory,
//            MessageChannel inboundChannel) {
//        TcpReceivingChannelAdapter tcpReceivingChannelAdapter = new TcpReceivingChannelAdapter();
//        tcpReceivingChannelAdapter.setConnectionFactory(connectionFactory);
//        tcpReceivingChannelAdapter.setOutputChannel(inboundChannel);
////        tcpReceivingChannelAdapter.setAutoStartup(true);
////        tcpReceivingChannelAdapter.setRetryInterval(5000);
//        return tcpReceivingChannelAdapter;
//    }
//
////    @Bean
////    public TcpSendingMessageHandler tcpSendingClientMessageHandler(
////            AbstractServerConnectionFactory connectionFactory) {
////        TcpSendingMessageHandler tcpSendingMessageHandler = new TcpSendingMessageHandler();
////        tcpSendingMessageHandler.setConnectionFactory(connectionFactory);
////        tcpSendingMessageHandler.setClientMode(true);
////        tcpSendingMessageHandler.setRetryInterval(5000);
////        tcpSendingMessageHandler.setLoggingEnabled(true);
////        return tcpSendingMessageHandler;
////    }
//
//    @Bean
//    public TcpInboundGateway tcpInboundGateway(
//            AbstractServerConnectionFactory connectionFactory) {
//        TcpInboundGateway tcpInboundGateway = new TcpInboundGateway();
//        tcpInboundGateway.setConnectionFactory(connectionFactory);
//        return tcpInboundGateway;
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

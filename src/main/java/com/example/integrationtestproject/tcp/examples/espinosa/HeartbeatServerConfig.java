//package com.example.integrationtestproject.tcp.espinosa;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.channel.QueueChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.ip.dsl.Tcp;
//import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
//import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
//import org.springframework.integration.ip.tcp.connection.*;
//import org.springframework.integration.ip.tcp.serializer.ByteArrayLengthHeaderSerializer;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.PollableChannel;
//
//@Slf4j
//@EnableIntegration
//@Configuration
//public class HeartbeatServerConfig {
//
//    @Value("${tcpServer.port}")
//    private int port;
//
//    @Bean
//    public MessageChannel outboundChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public PollableChannel inboundChannel() {
//        return new QueueChannel();
//    }
//
//    @Bean
//    public TcpNetServerConnectionFactory connectionFactory() {
//        TcpNetServerConnectionFactory connectionFactory = new TcpNetServerConnectionFactory(port);
//        connectionFactory.setSerializer(new ByteArrayLengthHeaderSerializer());
//        connectionFactory.setDeserializer(new ByteArrayLengthHeaderSerializer());
//        return connectionFactory;
//    }
//
//    @Bean
//    public TcpReceivingChannelAdapter heartbeatReceivingMessageAdapter(
//            TcpNetServerConnectionFactory connectionFactory,
//            MessageChannel outboundChannel) {
//        TcpReceivingChannelAdapter heartbeatReceivingMessageAdapter = new TcpReceivingChannelAdapter();
//        heartbeatReceivingMessageAdapter.setConnectionFactory(connectionFactory);
//        heartbeatReceivingMessageAdapter.setOutputChannel(outboundChannel);
//        return heartbeatReceivingMessageAdapter;
//    }
//
//    @Bean
//    public TcpSendingMessageHandler heartbeatSendingMessageHandler(
//            TcpNetServerConnectionFactory connectionFactory) {
//        TcpSendingMessageHandler heartbeatSendingMessageHandler = new TcpSendingMessageHandler();
//        heartbeatSendingMessageHandler.setConnectionFactory(connectionFactory);
//        return heartbeatSendingMessageHandler;
//    }
//
//    @Bean
//    public IntegrationFlow heartbeatServerFlow(
//            TcpReceivingChannelAdapter heartbeatReceivingMessageAdapter,
//            TcpSendingMessageHandler heartbeatSendingMessageHandler,
//            MessageChannel outboundChannel,
//            PollableChannel inboundChannel) {
//        return IntegrationFlow // ??????????????????????????????????????????
//                .from(Tcp.inboundGateway(Tcp.netServer(port)).requestChannel(inboundChannel))
//                .channel(outboundChannel)
//                .get();
//    }
//
//    @Bean
//    public HeartbeatServer heartbeatServer(
//            PollableChannel inboundChannel,
//            MessageChannel outboundChannel) {
//        return new HeartbeatServer(inboundChannel, outboundChannel);
//    }
//}

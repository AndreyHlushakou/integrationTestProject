package com.example.integrationtestproject.tcp.my;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.*;
import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
import org.springframework.messaging.MessageChannel;

@Slf4j
@Configuration
@EnableIntegration
@IntegrationComponentScan
public class TestIntegrationConfig {

    @Value("${tcpServer.port}")
    private int port;

    @Bean
    public MessageChannel inboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outboundChannel() {
        return new DirectChannel();
    }

    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory() {
        AbstractServerConnectionFactory serverConnectionFactory = new TcpNetServerConnectionFactory(port);
        AbstractByteArraySerializer serializer = new MyByteArraySerializer();
        serverConnectionFactory.setSerializer(serializer);
        serverConnectionFactory.setDeserializer(serializer);
        serverConnectionFactory.setSingleUse(false);
        return serverConnectionFactory;
    }

    @Bean
    public TcpReceivingChannelAdapter tcpReceivingChannelAdapter(
            AbstractServerConnectionFactory serverConnectionFactory,
            MessageChannel inboundChannel) {
        TcpReceivingChannelAdapter tcpReceivingChannelAdapter = new TcpReceivingChannelAdapter();
        tcpReceivingChannelAdapter.setConnectionFactory(serverConnectionFactory);
        tcpReceivingChannelAdapter.setOutputChannel(inboundChannel);
        return tcpReceivingChannelAdapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "outboundChannel")
    public TcpSendingMessageHandler tcpSendingClientMessageHandler(
            AbstractServerConnectionFactory serverConnectionFactory) {
        TcpSendingMessageHandler tcpSendingMessageHandler = new TcpSendingMessageHandler();
        tcpSendingMessageHandler.setConnectionFactory(serverConnectionFactory);
        return tcpSendingMessageHandler;
    }


    @EventListener
    public void handleTcpConnectionOpen(TcpConnectionOpenEvent event) {
        System.out.println("Client connected: " + event.getConnectionId());
    }

    @EventListener
    public void handleTcpConnectionClose(TcpConnectionCloseEvent event) {
        System.out.println("Client disconnected: " + event.getConnectionId());
    }

//    @Filter(inputChannel = "inboundChannel")
//    @Bean
//    MessageFilter filter() {
//        MessageFilter filter = new MessageFilter(new MessageSelector() {
//            @Override
//            public boolean accept(Message<?> message) {
//                byte[] arr = (byte[]) message.getPayload();
//                return arr.length != 0;
//            }
//        });
//        filter.setOutputChannelName("filterChannel");
////        filter.setDiscardChannelName("nullChannel"); // на тот случай если в сериализаторе вместо throw будет возвращать пустые сообщения
//        return filter;
//    }

}

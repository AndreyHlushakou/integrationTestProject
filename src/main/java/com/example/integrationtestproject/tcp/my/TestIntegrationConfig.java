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
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpConnectionCloseEvent;
import org.springframework.integration.ip.tcp.connection.TcpConnectionOpenEvent;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
import org.springframework.messaging.MessageChannel;

import static com.example.integrationtestproject.tcp.my.SimpleService.bytesToHex;

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
        return serverConnectionFactory;
    }

    @Bean
    public TcpReceivingChannelAdapter tcpReceivingChannelAdapter(
            AbstractServerConnectionFactory serverConnectionFactory,
            MessageChannel inboundChannel) {
        TcpReceivingChannelAdapter tcpReceivingChannelAdapter = new TcpReceivingChannelAdapter();
        tcpReceivingChannelAdapter.setConnectionFactory(serverConnectionFactory);
        tcpReceivingChannelAdapter.setOutputChannel(inboundChannel);
        tcpReceivingChannelAdapter.setAutoStartup(true);
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

//    @ServiceActivator(inputChannel = "inboundChannel")
//    public void logMessage(byte[] message) {
//        log.info("messageReceiver:{}", bytesToHex(message));
//    }

}

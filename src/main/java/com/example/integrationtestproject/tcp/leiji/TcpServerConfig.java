package com.example.integrationtestproject.tcp.leiji;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpConnectionCloseEvent;
import org.springframework.integration.ip.tcp.connection.TcpConnectionOpenEvent;
import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
import org.springframework.integration.ip.tcp.serializer.ByteArrayStxEtxSerializer;

@RequiredArgsConstructor
@Configuration
@EnableIntegration
@Slf4j
public class TcpServerConfig {

    public static final String MESSAGE_CHANNEL = "message-channel";

    @Value("${tcpServer.port}")
    private int serverPort;

    @Bean
    public AbstractServerConnectionFactory serverFactory() {
        AbstractServerConnectionFactory factory = new TcpNetServerConnectionFactory(serverPort);
        factory.setSerializer(new ByteArrayRawSerializer());
        factory.setDeserializer(new ByteArrayRawSerializer());

        return factory;
    }

    @Bean
    public TcpInboundGateway inboundGateway(AbstractServerConnectionFactory serverFactory) {
        TcpInboundGateway inbound = new TcpInboundGateway();
        inbound.setConnectionFactory(serverFactory);
        inbound.setRequestChannelName(MESSAGE_CHANNEL);
//        inbound.setLoggingEnabled(true);
        return inbound;
    }

    @EventListener
    public void handleTcpConnectionOpen(TcpConnectionOpenEvent event) {
        log.info("Client connected: {}", event.getConnectionId());
    }

    @EventListener
    public void handleTcpConnectionClose(TcpConnectionCloseEvent event) {
        log.info("Client disconnected: {}", event.getConnectionId());
    }

}

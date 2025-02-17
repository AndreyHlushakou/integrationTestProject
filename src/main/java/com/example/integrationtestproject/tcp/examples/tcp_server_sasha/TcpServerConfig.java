package com.example.integrationtestproject.tcp.examples.tcp_server_sasha;//package com.agat.server_epu.config.tcp.tcp_server;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.annotation.Transformer;
//import org.springframework.integration.channel.PriorityChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.ip.dsl.Tcp;
//import org.springframework.integration.ip.tcp.TcpInboundGateway;
//import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
//import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
//import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
//import org.springframework.integration.support.MessageBuilder;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.PollableChannel;
//
//@RequiredArgsConstructor
//@Configuration
//@EnableIntegration
//@IntegrationComponentScan
//public class TcpServerConfig {
//
//    @Value("${tcpServer.port}")
//    private static int serverPort;
//
//    public static final String TCP_CHANNEL_NAME = "plomba_box";
//
//    @Bean
//    public TcpNetServerConnectionFactory serverFactory() {
//        TcpNetServerConnectionFactory factoryServer = new TcpNetServerConnectionFactory(serverPort);
//        factoryServer.setSerializer(new ByteArrayRawSerializer());
//        factoryServer.setDeserializer(new ByteArrayRawSerializer());
//        return factoryServer;
//    }
//
//    @Bean
//    public PollableChannel priorityChannel() {
//        return new PriorityChannel();
//    }
//
//    @Bean
//    public IntegrationFlow tcpServerFlow() {
//        return IntegrationFlow.
//                from(Tcp.inboundAdapter(serverFactory()))
//                .channel(priorityChannel()) // Направляем сообщения в PriorityChannel
//                .get();
//    }
//
//    @Bean
//    public IntegrationFlow priorityChannelFlow() {
//
//        return IntegrationFlow
//                .from(priorityChannel())
//                .handle(message -> {
//                    System.out.println("Обработано сообщение с приоритетом: " + message.getHeaders().get("priority"));
//                    System.out.println("Сообщение: " + new String((byte[]) message.getPayload()));
//                })
//                .get();
//    }
//
//
//    private  final MessageChannel priorityChannel;
//
//    public void sendMessageWithPriority(String payload, int priority) {
//        Message<String> message = MessageBuilder
//                .withPayload(payload)
//                .setHeader("priority", priority) // Установка приоритета
//                .build();
//        priorityChannel.send(message);
//    }
//
//
//
//    @Bean
//    public PollableChannel processingChannel() {
//        return new PriorityChannel();
//    }
//
//    @Bean
//    public TcpInboundGateway tcpInboundGateway(AbstractServerConnectionFactory serverConnectionFactory) {
//        TcpInboundGateway gateway = new TcpInboundGateway();
//        gateway.setConnectionFactory(serverConnectionFactory);
//        gateway.setRequestChannel(priorityChannel());
//        return gateway;
//    }
//
//    @Bean
//    @Transformer(inputChannel = "inputChannel", outputChannel = "processingChannel")
//    public Jt808Message transformToJt808Message() {
//        byte[] payload = (byte[]) priorityChannel().receive().getPayload();
//
//
//        if (payload[0] != 0x7E || payload[payload.length - 1] != 0x7E) {
//            throw new IllegalArgumentException("Неверный формат сообщения");
//        }
//
//        byte startByte = payload[0];
//        byte endByte = payload[payload.length - 1];
//
//        return new Jt808Message(startByte, endByte);
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "processingChannel")
//    public byte messageProcessor(Jt808Message message) {
//        System.out.println("Получено сообщение JT808: " + message.getStartByte());
//        return  message.getStartByte();
//    }
//
//}


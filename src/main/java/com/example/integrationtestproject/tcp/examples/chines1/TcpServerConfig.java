//package com.example.integrationtestproject.tcp.examples.chines1;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.MessagingGateway;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
//import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
//import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
//import org.springframework.integration.ip.tcp.connection.TcpNetServerConnectionFactory;
//import org.springframework.integration.ip.tcp.serializer.AbstractByteArraySerializer;
//import org.springframework.integration.ip.tcp.serializer.ByteArrayRawSerializer;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.MessageHandler;
//import org.springframework.messaging.support.MessageBuilder;
//
//import java.util.Arrays;
//
//@EnableIntegration
//@Configuration
//public class TcpServerConfig {
//
//    private final TcpGateway tcpGateway;
//
//    @Value("${tcpServer.port}")
//    private int port;
//
//    public TcpServerConfig(TcpGateway tcpGateway) { //注入网关，用于发送消息
//        this.tcpGateway = tcpGateway;
//    }
//
//    @Bean
//    public MessageChannel serverIn() { //入站通道
//        return new DirectChannel();
//    }
//
//    @Bean public AbstractServerConnectionFactory serverCF() {
////        AbstractServerConnectionFactory serverConnectionFactory = new TcpNetServerConnectionFactory(port);
////        AbstractByteArraySerializer serializer = new ByteArrayRawSerializer();
////        serverConnectionFactory.setSerializer(serializer);
////        serverConnectionFactory.setDeserializer(serializer);
////        return serverConnectionFactory;
//        return new TcpNetServerConnectionFactory(this.port);
//    }
//
//    @Bean
//    public TcpReceivingChannelAdapter tcpInAdapter(AbstractServerConnectionFactory connectionFactory) { //入站适配器
//        TcpReceivingChannelAdapter inGate = new TcpReceivingChannelAdapter();
//        inGate.setConnectionFactory(connectionFactory);
//        inGate.setOutputChannelName("serverIn");
//        return inGate;
//    }
//
//    @ServiceActivator(inputChannel = "serverIn") //消息处理器
//    public void upCase(Message<byte[]> in) { //不直接用String或byte[]的原因是消息头的ip_connectionId字段，用来表明应答由哪个连接发送
//        System.out.println(in.getHeaders().get("ip_connectionId") + "=" + Arrays.toString(in.getPayload()));
//        Message<byte[]> reply = MessageBuilder
//                .createMessage(new byte[]{0x11, 0x22, 0x33}, in.getHeaders()); //同样简单逻辑：转成大写应答
//        tcpGateway.send(reply);
//    }
//
//    @Bean public MessageChannel serverOut() { //出站通道
//        return new DirectChannel();
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "serverOut")
//    public MessageHandler tcpOutAdapter(AbstractServerConnectionFactory connectionFactory) { //出站适配器
//        TcpSendingMessageHandler outGate = new TcpSendingMessageHandler();
//        outGate.setConnectionFactory(connectionFactory);
//        return outGate;
//    }
//
//    @MessagingGateway(defaultRequestChannel = "serverOut") //出站消息网关
//    public interface TcpGateway {
//        void send(Message<byte[]> out);
//    }
//
//}

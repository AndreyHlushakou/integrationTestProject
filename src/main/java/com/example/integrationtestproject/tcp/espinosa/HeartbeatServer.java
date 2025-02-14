//package com.example.integrationtestproject.tcp.espinosa;//package com.agat.server_epu.config.tcp.tcp_server2;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.PollableChannel;
//import org.springframework.messaging.support.GenericMessage;
//
//import java.util.concurrent.Executors;
//
//@Slf4j
//public class HeartbeatServer {
//    private final PollableChannel inboundChannel;
//    private final MessageChannel outboudChannel;
//
//    public HeartbeatServer(PollableChannel inboundChannel, MessageChannel outboudChannel) {
//        this.inboundChannel = inboundChannel;
//        this.outboudChannel = outboudChannel;
//    }
//
//    @EventListener
//    public void initializaAfterContextIsReady(ContextRefreshedEvent event) {
//        log.info("Starting Heartbeat");
//        start();
//    }
//
//    public void start() {
//        Executors.newSingleThreadExecutor().execute(() -> {
//            while (true) {
//                try {
//                    Message<?> message = inboundChannel.receive(1000);
//                    if (message == null) {
//                        log.error("Heartbeat timeouted");
//                    } else {
//                        String messageStr = new String((byte[]) message.getPayload());
//                        if (messageStr.equals("status")) {
//                            log.info("Heartbeat received");
//                            outboudChannel.send(new GenericMessage<>("OK"));
//                            System.out.println("3333");
//                        } else {
//                            System.out.println("2222");
////                            log.error("Unexpected message content from client: " + messageStr);
//                        }
//                    }
//
//                } catch (Exception e) {
////                    System.out.println("1111");
//                    log.error(String.valueOf(e));
//                }
//            }
//        });
//    }
//}

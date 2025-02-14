package com.example.integrationtestproject.tcp.tcp_server_sasha;//package com.agat.server_epu.config.tcp.tcp_server;
//
//
//import com.agat.server_epu.httpserver.entities.LogTcpEntity;
//import com.agat.server_epu.tcpserver.AddressMessage;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.integration.ip.IpHeaders;
//import org.springframework.integration.support.MessageBuilder;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageHeaders;
//
//import java.nio.charset.StandardCharsets;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Queue;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
//@Slf4j
//@RequiredArgsConstructor
//public class TcpServerInboundEndpointMessage {
//
//
//
//
//    private final Queue<LogTcpEntity> queueLogTcp = new ConcurrentLinkedQueue<>();
//    private final Queue<AddressMessage> messagesForClients = new ArrayBlockingQueue<>(1000);
//    private final Queue<byte[]> queuePositionData = new ConcurrentLinkedQueue<>();
//
//
//
////    public List<Message<byte[]>> handleTcpRequest(Message<byte[]> messageFromPlomba) {
////        // Build a message
////        String message =
////                "From:"
////                        + messageFromPlomba.getHeaders().get(IpHeaders.IP_ADDRESS)
////                        + ", messageFromClient.length: "
////                        + messageFromPlomba.getPayload().length;
////
////        //Add to log
////        addLodInDB(new LogTcpEntity(), message);
////        // Show length message
////        log.info(message);
////
////        //Receive byte array from tcp ip
////        byte[] requestMessage = messageFromPlomba.getPayload();
////
////        boolean res = false;
////        StringBuilder messageLogRequestResponse = new StringBuilder();
////        String strRequestBaseMessage = new String(requestMessage, StandardCharsets.US_ASCII);
////
////        //Array for response
////        byte[] responseMessage = new byte[]{};
////
////        // Splitter — create some messages
////        MessageHeaders headers = messageFromPlomba.getHeaders();
////        Message<byte[]> message1 = null;
////        Message<byte[]> message2 = null;
////        Message<byte[]> message3 = null;
////
////        int serialNumber = 0;
////        String deviceId;
////
////        boolean isBASE = strRequestBaseMessage.startsWith("BASE", 20);
////
////        if (isBASE) {
////
////            deviceId = strRequestBaseMessage.substring(1, 13);
////            message = "strRequestBaseMessage: " + strRequestBaseMessage;
////            addLodInDB(new LogTcpEntity(), message, deviceId);
////            log.info(message);
////
////            String typeBase = strRequestBaseMessage.substring(20, 26);
////
////            switch (typeBase) {
////                case "BASE,2": {
////
////                    if (strRequestBaseMessage.substring(27).equals("TIME)")) {  //проверяем что это запрос на время
////                        String dateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
////
////                        String strResponseBaseMessage = strRequestBaseMessage.substring(0, 27) + dateTime + ")"; //формируем ответ
////                        log.info("strResponseBaseMessage:{}", strResponseBaseMessage);
////
////                        responseMessage = strResponseBaseMessage.getBytes(StandardCharsets.US_ASCII);
////                        res = true;
////
////                        log.debug("Scheduled for sending to the client:{}, result:{}", messageFromPlomba.getHeaders().get(IpHeaders.IP_ADDRESS), responseMessage); //запланировано к отправке
////
////                        message1 = MessageBuilder
////                                .withPayload(responseMessage)
////                                .copyHeaders(headers)
////                                .build();
////
////                    } else {
////                        log.info("response client BASE2 - OK"); //ответ от пломбы о том что она получила время от нас
////                    }
////
////                }
////                break;
////
////                default: { //данная BASE команда не обрабатывается сервером либо некорректна
////                    log.warn(
////                            String.format(
////                                    "MESSAGE ERROR - this BASE command is not processed by the server or it is incorrect:" +
////                                    "\nstrRequestBaseMessage   :%s", strRequestBaseMessage));
////                }
////            }
////        } else {
////
////            messageHandler.handling(requestMessage); // обрабатываем сообщение на правильность и создаем ответное сообщение
////            responseMessage = messageHandler.getResponseMessage();
////            res = messageHandler.isRes(); // правильное ли пришло сообщение, чтобы на него отвечать
////
////            if (res) {
////                message2 = MessageBuilder
////                        .withPayload(responseMessage)
////                        .copyHeaders(headers)
////                        .build();
////
////                if (messageHandler.isPositionData()) {
////                    message3 = MessageBuilder
////                            .withPayload(responseMessage)
////                            .copyHeaders(headers)
////                            .build();
////                }
////                }
////
////            deviceId = messageHandler.getDeviceId();
////            serialNumber = messageHandler.getMessageSerialNumber();
////
////            messageLogRequestResponse
////                    .append("\nDevice_Id   : ").append(deviceId)
////                    .append("\nSerialNumber: ").append(serialNumber);
////
////            if (messageHandler.isPositionData()) {
////                String dateTimeFromPackage = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(messageHandler.getDateTime());
////                messageLogRequestResponse.append("\nDate_Time   : ").append(dateTimeFromPackage);
////            }
////
////            if (!res) {
////                message = messageHandler.getErrorMessage();
////                addLodInDB(new LogTcpEntity(), message, deviceId);
////            }
////        }
////
////        message = "to:" + messageFromPlomba.getHeaders().get(IpHeaders.IP_ADDRESS) + ", sending - " + res;
////        addLodInDB(new LogTcpEntity(), message, deviceId);
////        // выводим сообщение о состоянии ответа клиенту
////        log.info(message);
////
////        StringBuilder report = getReportAboutMessage(isBASE, requestMessage, responseMessage);
////
////        message = messageLogRequestResponse.append(report).toString();
////        if (isBASE) {
////            addLodInDB(new LogTcpEntity(), message, deviceId);
////        } else {
////            addLodInDB(new LogTcpEntity(), message, deviceId, serialNumber);
////        }
////        log.info(message); // выводим сообщения принятые и отправленные
////
//////         Возвращаем список сообщений
////        return List.of(message1, message2, message3);
////
////    }
//
//    private void addLodInDB(LogTcpEntity logTcpEntity, String message) {
//        logTcpEntity.setDateTime(new Date());
//        logTcpEntity.setMessage(message);
//        queueLogTcp.add(logTcpEntity);
//    }
//
//    private void addLodInDB(LogTcpEntity logTcpEntity, String message, String deviceId) {
//        addLodInDB(logTcpEntity, message, deviceId, null);
//    }
//
//    private void addLodInDB(LogTcpEntity logTcpEntity, String message, String deviceId, Integer serialNumber) {
//        logTcpEntity.setSerialNumber(serialNumber);
//        logTcpEntity.setDeviceId(deviceId);
//        addLodInDB(logTcpEntity, message);
//    }
//
//
////    private StringBuilder getReportAboutMessage(boolean isBASE, byte[] requestMessage, byte[] responseMessage) {
////        StringBuilder stringBuilder = new StringBuilder("\nClientsMessage:\n");
////        if (isBASE) {
////            for (byte datum : requestMessage) {
////                stringBuilder.append(String.format("%02X ", datum));
////            }
////
////            stringBuilder.append("\nServerMessage:\n");
////
////            for (byte datum : responseMessage) {
////                stringBuilder.append(String.format("%02X ", datum));
////            }
////
////            stringBuilder.append("\n");
////        } else {
////
////            stringBuilder.append("withEscapeChar   :");
////            for (byte datum : requestMessage) {
////                stringBuilder.append(String.format("%02X ", datum));
////            }
////            stringBuilder.append("\nwithoutEscapeChar:");
////            for (byte datum : messageHandler.getRequestMessageWithoutEscape()) {
////                stringBuilder.append(String.format("%02X ", datum));
////            }
////
////            stringBuilder.append("\nServerMessage:\n");
////
////            stringBuilder.append("withEscapeChar   :");
////            for (byte datum : responseMessage) {
////                stringBuilder.append(String.format("%02X ", datum));
////            }
////            stringBuilder.append("\nwithoutEscapeChar:");
////            for (byte datum : messageHandler.getResponseMessageWithoutEscape()) {
////                stringBuilder.append(String.format("%02X ", datum));
////            }
//////            stringBuilder.append("\n");
////
////        }
////        return stringBuilder;
////    }
//
//
//    public boolean send(Object object, byte[] data) { //метод отправки сообщений клиенту
//        var result = messagesForClients.offer(new AddressMessage(object, data)); // чтобы не заблокировать поток, складываем сообщения в очередь
//        log.debug("Scheduled for sending to the client:{}, result:{}", object, result); //запланировано к отправке
//        return result;
//    }
//
//
//
//}
//

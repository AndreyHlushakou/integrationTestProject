//package com.example.integrationtestproject.tcp.examples.tutorial;
//
//import com.example.integrationtestproject.tcp.examples.tutorial.t5Router.Address;
//import com.example.integrationtestproject.tcp.examples.tutorial.t3Transformer.Student;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.annotation.Filter;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.annotation.ServiceActivator;
//import org.springframework.integration.annotation.Transformer;
//import org.springframework.integration.channel.DirectChannel;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.core.MessageSelector;
//import org.springframework.integration.filter.MessageFilter;
//import org.springframework.integration.json.JsonToObjectTransformer;
//import org.springframework.integration.json.ObjectToJsonTransformer;
//import org.springframework.integration.router.HeaderValueRouter;
//import org.springframework.integration.router.PayloadTypeRouter;
//import org.springframework.integration.router.RecipientListRouter;
//import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
//import org.springframework.integration.transformer.HeaderEnricher;
//import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
//import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableIntegration
//@IntegrationComponentScan
//public class IntegrationConfig {
//
//    //t2//begin//
//    @Bean
//    public MessageChannel receiverChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel replyChannel() {
//        return new DirectChannel();
//    }
//    //t2//end//
//
//
//    //t3//begin//
//    @Bean
//    @Transformer(inputChannel = "integration.student.gateway.channel",
//            outputChannel = "integration.student.objectToJson.channel")
//    public ObjectToJsonTransformer objectToJsonTransformer() {
//        return new ObjectToJsonTransformer(getMapper());
//    }
//
//    @Bean
//    public Jackson2JsonObjectMapper getMapper() {
//        ObjectMapper mapper = new ObjectMapper();
//        return new Jackson2JsonObjectMapper(mapper);
//    }
//
//    @Bean
//    @Transformer(inputChannel = "integration.student.jsonToObject.channel",
//            outputChannel = "integration.student.jsonToObject.fromTransformer.channel")
//    public JsonToObjectTransformer jsonToObjectTransformer() {
//        return new JsonToObjectTransformer(Student.class);
//    }
//    //t3//end//
//
//
//    //t4//begin//
//    @Bean
//    @Transformer(inputChannel = "integration.student.gateway.channel",
//            outputChannel = "integration.student.toConvertObject.channel")
//    public HeaderEnricher enricherHeader() {
//        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
//        headersToAdd.put("header1", new StaticHeaderValueMessageProcessor<>("Test Header 1"));
//        headersToAdd.put("header2", new StaticHeaderValueMessageProcessor<>("Test Header 2"));
//        HeaderEnricher enricher = new HeaderEnricher(headersToAdd);
//        return enricher;
//    }
//
//    @Bean
//    @Transformer(inputChannel = "integration.student.toConvertObject.channel",
//            outputChannel = "integration.student.objectToJson.channel")
//    public ObjectToJsonTransformer objectToJsonTransformer4() {
//        return new ObjectToJsonTransformer(getMapper());
//    }
//    //t4//end//
//
//
//    //t5//begin//
//    @ServiceActivator(inputChannel = "router.channel")
//    @Bean
//    public PayloadTypeRouter router() {
//        PayloadTypeRouter router = new PayloadTypeRouter();
//        router.setChannelMapping(Student.class.getName(), "student.channel");
//        router.setChannelMapping(Address.class.getName(), "address.channel");
//        return router;
//    }
//    //t5//end//
//
//
//    //t6//begin//
//    @ServiceActivator(inputChannel = "router.list.channel")
//    @Bean
//    public RecipientListRouter routerList() {
//        RecipientListRouter router = new RecipientListRouter();
//        router.addRecipient("student.channel.1");
//        router.addRecipient("student.channel.2");
//        return router;
//    }
//    //t6//end//
//
//
//    //t7//begin//
//    @ServiceActivator(inputChannel = "router.header.channel")
//    @Bean
//    public PayloadTypeRouter routerHeader() {
//        PayloadTypeRouter router = new PayloadTypeRouter();
//        router.setChannelMapping(Student.class.getName(), "student.enrich.header.channel");
//        router.setChannelMapping(Address.class.getName(), "address.enrich.header.channel");
//        return router;
//    }
//
//    @Bean
//    @Transformer(inputChannel = "student.enrich.header.channel", outputChannel = "header.payload.router.channel")
//    public HeaderEnricher enrichHeaderForStudent() {
//        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
//        headersToAdd.put("testHeader", new StaticHeaderValueMessageProcessor<>("student"));
//        HeaderEnricher enricher = new HeaderEnricher(headersToAdd);
//        return enricher;
//    }
//
//    @Bean
//    @Transformer(inputChannel = "address.enrich.header.channel", outputChannel = "header.payload.router.channel")
//    public HeaderEnricher enrichHeaderForAddress() {
//        Map<String, HeaderValueMessageProcessor<String>> headersToAdd = new HashMap<>();
//        headersToAdd.put("testHeader", new StaticHeaderValueMessageProcessor<>("address"));
//        HeaderEnricher enricher = new HeaderEnricher(headersToAdd);
//        return enricher;
//    }
//
//    @ServiceActivator(inputChannel = "header.payload.router.channel")
//    @Bean
//    public HeaderValueRouter headerRouter() {
//        HeaderValueRouter router = new HeaderValueRouter("testHeader");
//        router.setChannelMapping("student", "student.channel");
//        router.setChannelMapping("address", "address.channel");
//        return router;
//    }
//    //t7//end//
//
//    //t8//begin//
//    @Filter(inputChannel = "router.filter.channel")
//    @Bean
//    MessageFilter filter() {
//        MessageFilter filter = new MessageFilter(new MessageSelector() {
//            @Override
//            public boolean accept(Message<?> message) {
//                return message.getPayload() instanceof Student;
//            }
//        });
//        filter.setOutputChannelName("student.channel");
//        return filter;
//    }
//    //t8//end//
//}

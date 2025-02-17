//package com.example.integrationtestproject.tcp.examples.tutorial.t2Gateway;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/integrate")
//public class IntegrationController2 {
//
//    private final IntegrationGateway2 integrationGateway2;
//
//    public IntegrationController2(IntegrationGateway2 integrationGateway2) {
//        this.integrationGateway2 = integrationGateway2;
//    }
//
//
//    //t2//begin//
//    @GetMapping(value = "{name}")
//    public String getMessageFromIntegration(@PathVariable("name") String name) {
//        return integrationGateway2.sendMessage(name);
//    }
//    //t2//end//
//
//}

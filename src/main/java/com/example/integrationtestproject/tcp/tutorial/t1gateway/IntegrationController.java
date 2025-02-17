//package com.example.integrationtestproject.tcp.tutorial.t1gateway;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/integrate")
//public class IntegrationController {
//
//    @Autowired
//    private IntegrationGateway integrationGateway;
//
//    @GetMapping(value = "{name}")
//    public String getMessageFromIntegration(@PathVariable("name") String name) {
//        return integrationGateway.sendMessage(name);
//    }
//
//}

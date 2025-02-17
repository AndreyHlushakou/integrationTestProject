//package com.example.integrationtestproject.tcp.examples.tutorial.t3Transformer;
//
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/integrate")
//public class IntegrationController3 {
//
//    private final IntegrationGateway3 integrationGateway3;
//
//    public IntegrationController3(IntegrationGateway3 integrationGateway3) {
//        this.integrationGateway3 = integrationGateway3;
//    }
//
//    //t3//begin//
//    @PostMapping
//    public String processStudentDetails(@RequestBody Student student) {
//        return integrationGateway3.processStudentDetails(student);
//    }
//    //3//end//
//
//}

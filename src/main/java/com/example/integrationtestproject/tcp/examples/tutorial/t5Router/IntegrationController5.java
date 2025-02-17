//package com.example.integrationtestproject.tcp.examples.tutorial.t5Router;
//
//import com.example.integrationtestproject.tcp.examples.tutorial.t3Transformer.Student;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/integrate")
//public class IntegrationController5 {
//
//    private final IntegrationGateway5 integrationGateway5;
//
//    public IntegrationController5(IntegrationGateway5 integrationGateway5) {
//        this.integrationGateway5 = integrationGateway5;
//    }
//
//    //t5//begin//
//    @PostMapping("/student5")
//    public void processStudentDetails5(@RequestBody Student student) {
//        integrationGateway5.process(student);
//    }
//
//    @PostMapping("/address5")
//    public void processAddressDetails(@RequestBody Address address) {
//        integrationGateway5.process(address);
//    }
//    //t5//end//
//
//}

//package com.example.integrationtestproject.tcp.examples.tutorial.t7RouterHeader;
//
//import com.example.integrationtestproject.tcp.examples.tutorial.t5Router.Address;
//import com.example.integrationtestproject.tcp.examples.tutorial.t3Transformer.Student;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/integrate")
//public class IntegrationController7 {
//
//    private final IntegrationGateway7 integrationGateway7;
//
//    public IntegrationController7(IntegrationGateway7 integrationGateway7) {
//        this.integrationGateway7 = integrationGateway7;
//    }
//
//    //t7//begin//
//    @PostMapping("/student7")
//    public void processStudentDetails7(@RequestBody Student student) {
//        integrationGateway7.processHeader(student);
//    }
//
//    @PostMapping("/address7")
//    public void processAddressDetails7(@RequestBody Address address) {
//        integrationGateway7.processHeader(address);
//    }
//    //t7//end//
//
//}

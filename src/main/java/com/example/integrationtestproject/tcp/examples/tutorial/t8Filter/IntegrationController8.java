//package com.example.integrationtestproject.tcp.examples.tutorial.t8Filter;
//
//import com.example.integrationtestproject.tcp.examples.tutorial.t5Router.Address;
//import com.example.integrationtestproject.tcp.examples.tutorial.t3Transformer.Student;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/integrate")
//public class IntegrationController8 {
//
//    private final IntegrationGateway8 integrationGateway8;
//
//    public IntegrationController8(IntegrationGateway8 integrationGateway8) {
//        this.integrationGateway8 = integrationGateway8;
//    }
//
//    //t7//begin//
//    @PostMapping("/student8")
//    public void processStudentDetails8(@RequestBody Student student) {
//        integrationGateway8.processFilter(student);
//    }
//
//    @PostMapping("/address8")
//    public void processAddressDetails8(@RequestBody Address address) {
//        integrationGateway8.processFilter(address);
//    }
//    //t7//end//
//
//}

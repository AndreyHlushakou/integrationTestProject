//package com.example.integrationtestproject.tcp.examples.tutorial.t6RouterList;
//
//import com.example.integrationtestproject.tcp.examples.tutorial.t3Transformer.Student;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/integrate")
//public class IntegrationController6 {
//
//    private final IntegrationGateway6 integrationGateway6;
//
//    public IntegrationController6(IntegrationGateway6 integrationGateway6) {
//        this.integrationGateway6 = integrationGateway6;
//    }
//
//    //t6//begin//
//    @PostMapping("/student6")
//    public void processStudentDetails6(@RequestBody Student student) {
//        integrationGateway6.processList(student);
//    }
//    //t6//end//
//
//}

package com.example.integrationtestproject.tcp.tutorial.t2Gateway;

import com.example.integrationtestproject.tcp.tutorial.t3Transformer.Student;
import com.example.integrationtestproject.tcp.tutorial.t5Router.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrate")
public class IntegrationController {

    private final IntegrationGateway integrationGateway;

    public IntegrationController(IntegrationGateway integrationGateway) {
        this.integrationGateway = integrationGateway;
    }

    //t2//begin//
    @GetMapping(value = "{name}")
    public String getMessageFromIntegration(@PathVariable("name") String name) {
        return integrationGateway.sendMessage(name);
    }
    //t2//end//

    //t3//begin//
    @PostMapping
    public String processStudentDetails(@RequestBody Student student) {
        return integrationGateway.processStudentDetails(student);
    }
    //3//end//

    //t5//begin//
    @PostMapping("/student")
    public void processStudentDetails5(@RequestBody Student student) {
        integrationGateway.process(student);
    }

    @PostMapping("/address")
    public void processAddressDetails(@RequestBody Address address) {
        integrationGateway.process(address);
    }
    //t5//end//

    //t6//begin//
    @PostMapping("/student2")
    public void processStudentDetails6(@RequestBody Student student) {
        integrationGateway.process2(student);
    }
    //t6//end//

}

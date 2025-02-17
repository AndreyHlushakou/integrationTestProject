package com.example.integrationtestproject.tcp.tutorial.t2transformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/integrate")
public class IntegrationController {

    @Autowired
    private IntegrationGateway integrationGateway;

    @GetMapping(value = "{name}")
    public String getMessageFromIntegration(@PathVariable("name") String name) {
        return integrationGateway.sendMessage(name);
    }

    @PostMapping
    public String processStudentDetails(@RequestBody Student student) {
        return integrationGateway.processStudentDetails(student);
    }

}

package com.example.integrationtestproject.tcp.tutorial.t5Router;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Address implements Serializable {
    @Serial
    private static final long serialVersionUID = -561356584135156L;

    private String street;
    private String city;
    private String state;
}

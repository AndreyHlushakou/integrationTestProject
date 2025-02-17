package com.example.integrationtestproject.tcp.tutorial.t3Transformer;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = -45645698456498749L;

    private String id;
    private String name;
    private String school;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}

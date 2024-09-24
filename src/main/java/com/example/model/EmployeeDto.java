package com.example.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;
}

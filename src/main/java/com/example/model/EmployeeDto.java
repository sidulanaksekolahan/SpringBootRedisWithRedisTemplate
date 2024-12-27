package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    private String firstName;

    private String lastName;

    private String email;

}

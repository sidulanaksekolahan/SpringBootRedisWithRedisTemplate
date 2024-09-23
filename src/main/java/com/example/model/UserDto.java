package com.example.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private String id;

    private String name;

    private int age;
}

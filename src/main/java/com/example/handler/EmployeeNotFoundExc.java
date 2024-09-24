package com.example.handler;

public class EmployeeNotFoundExc extends RuntimeException {

    public EmployeeNotFoundExc(String message) {
        super(message);
    }
}

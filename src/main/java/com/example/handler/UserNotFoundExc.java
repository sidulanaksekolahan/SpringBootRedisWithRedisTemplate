package com.example.handler;

public class UserNotFoundExc extends RuntimeException {

    public UserNotFoundExc(String message) {
        super(message);
    }
}

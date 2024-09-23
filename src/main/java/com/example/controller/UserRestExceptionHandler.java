package com.example.controller;

import com.example.handler.UserNotFoundExc;
import com.example.response.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundExc exc) {

        UserErrorResponse errorResponse = new UserErrorResponse(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

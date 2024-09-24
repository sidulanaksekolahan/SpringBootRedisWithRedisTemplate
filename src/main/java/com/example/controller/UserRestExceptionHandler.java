package com.example.controller;

import com.example.handler.EmployeeNotFoundExc;
import com.example.response.EmployeeErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundExc exc) {

        EmployeeErrorResponse errorResponse = new EmployeeErrorResponse(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

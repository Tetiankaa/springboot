package com.example.springboot.controllers;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@Valid
@RestControllerAdvice // It is used to handle exceptions globally across multiple controllers in a RESTful application.
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String sizeException(MethodArgumentNotValidException e){
        System.out.println(e);
        return e.getFieldError().getDefaultMessage();

    }
}

package com.example.springboot.controllers;

import com.example.springboot.dto.CustomerSizeExceptionDTO;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@Valid
@RestControllerAdvice // It is used to handle exceptions globally across multiple controllers in a RESTful application.
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CustomerSizeExceptionDTO sizeException(MethodArgumentNotValidException e){

        return new CustomerSizeExceptionDTO(400,e.getFieldError().getField(),e.getFieldError().getDefaultMessage());

    }
}

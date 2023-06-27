package com.example.springboot.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice //is used to define global exception handlers for your application.
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class) //typically thrown when there are validation errors in request parameters or request bodies
    public ResponseEntity<String> exceptionHandler(MethodArgumentNotValidException e){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("headerName","headerValue");

        return new ResponseEntity<>(e.getFieldError().getDefaultMessage(), httpHeaders, HttpStatus.BAD_REQUEST);
    //By returning a ResponseEntity object, you have more control over the HTTP response sent back to the client.
    }
}

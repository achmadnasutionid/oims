package com.oims.futureprogram.controller;

import com.oims.futureprogram.exception.ResourceNotFoundException;
import com.oims.futureprogram.model.ErrorCode;
import com.oims.futureprogram.model.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public Response resourceNotFoundException(ResourceNotFoundException ex) {
        return Response.builder().code(ex.getErrorCode()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = ValidationException.class)
    public Response validationException(ValidationException ex) {
        return Response.builder().code(ErrorCode.BAD_REQUEST.getCode()).message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = Exception.class)
    public Response exception() {
        return Response.builder().code(ErrorCode.INTERNAL_SERVER_ERROR.getCode()).message(ErrorCode.INTERNAL_SERVER_ERROR.getMessage()).build();
    }

}

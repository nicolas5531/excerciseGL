package com.globallogic.ejerciciobci.controllers.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> handleException(Exception exception) {
        ExceptionDTO ex = new ExceptionDTO(Collections.singletonList(new Error(400, exception.getMessage())));

        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDTO ex = new ExceptionDTO(Collections.singletonList(new Error(400, exception.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining())
        )));

        return new ResponseEntity<>(ex, HttpStatus.BAD_REQUEST);
    }
}

package com.victormora.labmarket.api.exceptions;

import java.time.LocalDateTime;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptions extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entitidadeNotFoundException(EntityNotFoundException e, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        DefaultError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, e, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> entidadeAlreadyExists(EntityExistsException e, WebRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        DefaultError.builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .message(e.getMessage())
                .build();

        return handleExceptionInternal(e, e, new HttpHeaders(), status, request);
    }

}

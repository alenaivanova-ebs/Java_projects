package com.runner.controller.exception;

import com.runner.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Response> handleException(EntityNotFoundException exc) {
        Response error = new Response(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                HttpStatus.NOT_FOUND.value() + exc.getMessage().substring(exc.getMessage().indexOf(":") + 2));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Response> handleException(IllegalArgumentException exc) {
        Response error = new Response(
                HttpStatus.NOT_ACCEPTABLE.value(),
                exc.getMessage(),
                HttpStatus.NOT_ACCEPTABLE.toString());
        return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler
    public ResponseEntity<Response> handleException(Exception exc) {
        Response error = new Response(
                HttpStatus.BAD_REQUEST.value(),
                exc.getMessage(),
                HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}






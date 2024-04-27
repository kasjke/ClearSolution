package com.example.clearsolutions.exception.handlers;

import com.example.clearsolutions.exception.IllStateException;
import com.example.clearsolutions.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorMessage> notFoundHandler(NotFoundException e) {
        return returnResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    ResponseEntity<ErrorMessage> returnResponse(String message, HttpStatus status) {
        log.error(message);
        ErrorMessage errorMessage = new ErrorMessage(message);
        return new ResponseEntity<>(errorMessage,status) ;
    }

    @ExceptionHandler(IllStateException.class)
    ResponseEntity<ErrorMessage> badRequestHandler(IllStateException e) {
        return returnResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
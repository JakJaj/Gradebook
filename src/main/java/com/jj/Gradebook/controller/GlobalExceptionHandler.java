package com.jj.Gradebook.controller;

import com.jj.Gradebook.controller.response.ExceptionResponse;
import com.jj.Gradebook.controller.response.FormatExceptionResponse;
import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchUserException;
import com.jj.Gradebook.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchUserException.class)
    private ResponseEntity<ExceptionResponse> handleNoSuchUserException(NoSuchUserException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionResponse.builder()
                        .status("Failure")
                        .message(ex.getMessage())
                        .status(HttpStatus.NOT_FOUND.name())
                        .build()
        );
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .status("Failure")
                        .message(ex.getMessage())
                        .status(HttpStatus.CONFLICT.name())
                        .build()
        );
    }

    @ExceptionHandler(DateFormatException.class)
    private ResponseEntity<FormatExceptionResponse> handleFormatExceptions(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                FormatExceptionResponse.builder()
                        .status("Failure")
                        .message(ex.getMessage())
                        .build()
        );
    }
}

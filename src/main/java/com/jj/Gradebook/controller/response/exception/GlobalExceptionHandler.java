package com.jj.Gradebook.controller.response.exception;

import com.jj.Gradebook.exceptions.DateFormatException;
import com.jj.Gradebook.exceptions.NoSuchEntityException;
import com.jj.Gradebook.exceptions.EntityAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchEntityException.class)
    private ResponseEntity<ExceptionResponse> handleNoSuchUserException(NoSuchEntityException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ExceptionResponse.builder()
                        .status("Failure")
                        .message(ex.getMessage())
                        .errorCode(HttpStatus.NOT_FOUND.getReasonPhrase())
                        .build()
        );
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    private ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(EntityAlreadyExistsException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ExceptionResponse.builder()
                        .status("Failure")
                        .message(ex.getMessage())
                        .errorCode(HttpStatus.CONFLICT.getReasonPhrase())
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

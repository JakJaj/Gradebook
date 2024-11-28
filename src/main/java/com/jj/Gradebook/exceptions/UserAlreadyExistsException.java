package com.jj.Gradebook.exceptions;

public class UserAlreadyExistsException extends IllegalArgumentException{
    public UserAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}

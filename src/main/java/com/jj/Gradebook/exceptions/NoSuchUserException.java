package com.jj.Gradebook.exceptions;

public class NoSuchUserException extends IllegalArgumentException{
    public NoSuchUserException(String errorMessage){
        super(errorMessage);
    }
}

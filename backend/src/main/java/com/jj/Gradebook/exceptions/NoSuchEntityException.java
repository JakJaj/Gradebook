package com.jj.Gradebook.exceptions;

public class NoSuchEntityException extends IllegalArgumentException{
    public NoSuchEntityException(String errorMessage){
        super(errorMessage);
    }
}

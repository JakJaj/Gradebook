package com.jj.Gradebook.exceptions;

public class EntityNotFoundException extends Exception{
    public EntityNotFoundException(String errorMessage){
        super(errorMessage);
    }
}

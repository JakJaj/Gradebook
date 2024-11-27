package com.jj.Gradebook.exceptions;

public class EntityNotFoundException extends IllegalArgumentException{
    public EntityNotFoundException(String errorMessage){
        super(errorMessage);
    }
}

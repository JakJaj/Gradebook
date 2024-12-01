package com.jj.Gradebook.exceptions;

public class EntityAlreadyExistsException extends IllegalArgumentException{
    public EntityAlreadyExistsException(String errorMessage){
        super(errorMessage);
    }
}

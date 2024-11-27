package com.jj.Gradebook.exceptions;

public class EntityAlreadyExistException extends IllegalArgumentException{
    public EntityAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}

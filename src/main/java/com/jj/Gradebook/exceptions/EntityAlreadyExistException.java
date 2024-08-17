package com.jj.Gradebook.exceptions;

public class EntityAlreadyExistException extends Exception{
    public EntityAlreadyExistException(String errorMessage){
        super(errorMessage);
    }
}

package com.jj.Gradebook.exceptions;

public class EntityListEmptyException extends IllegalStateException{
    public EntityListEmptyException(String errorMessage){
        super(errorMessage);
    }
}

package com.jj.Gradebook.exceptions;

public class EntityListEmptyException extends Exception{
    public EntityListEmptyException(String errorMessage){
        super(errorMessage);
    }
}

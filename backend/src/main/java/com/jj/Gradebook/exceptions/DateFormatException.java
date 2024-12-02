package com.jj.Gradebook.exceptions;

public class DateFormatException extends IllegalArgumentException{
    public DateFormatException(String errorMessage){
        super(errorMessage);
    }
}

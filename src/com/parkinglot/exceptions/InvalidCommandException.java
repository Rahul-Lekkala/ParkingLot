package com.parkinglot.exceptions;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(String errorMessage)
    {
        super(errorMessage);
    }
}

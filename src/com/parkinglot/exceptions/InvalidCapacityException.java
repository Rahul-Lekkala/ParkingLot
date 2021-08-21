package com.parkinglot.exceptions;

public class InvalidCapacityException extends RuntimeException {
    public InvalidCapacityException(String errorMessage)
    {
        super(errorMessage);
    }
}

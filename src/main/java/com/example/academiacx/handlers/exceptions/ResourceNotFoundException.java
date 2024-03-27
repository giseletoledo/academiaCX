package com.example.academiacx.handlers.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String mns) {
        super(mns);
    }

    public ResourceNotFoundException(String mns, Throwable causa) {
        super(mns, causa);
    }
}
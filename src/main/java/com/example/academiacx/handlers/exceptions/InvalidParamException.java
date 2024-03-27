package com.example.academiacx.handlers.exceptions;
public class InvalidParamException extends RuntimeException {

    public InvalidParamException(String mns) {
        super(mns);
    }

    public InvalidParamException(String mns, Throwable causa) {
        super(mns, causa);
    }
}

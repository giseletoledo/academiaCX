package com.example.academiacx.handlers.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException{

    public RecursoNaoEncontradoException(String mns) {
        super(mns);
    }

    public RecursoNaoEncontradoException(String mns, Throwable causa) {
        super(mns, causa);
    }
}
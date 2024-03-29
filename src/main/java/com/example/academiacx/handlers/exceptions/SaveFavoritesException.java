package com.example.academiacx.handlers.exceptions;

public class SaveFavoritesException extends RuntimeException {

    public SaveFavoritesException(String message) {
        super(message);
    }

    public SaveFavoritesException(String message, Throwable cause) {
        super(message, cause);
    }
}

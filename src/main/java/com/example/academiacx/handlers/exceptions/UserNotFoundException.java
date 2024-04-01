package com.example.academiacx.handlers.exceptions;

public class UserNotFoundException extends ResourceNotFoundException {

    public UserNotFoundException(Long id) {
        super("User with ID " + id + " not found");
    }
}


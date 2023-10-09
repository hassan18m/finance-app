package com.devhassan.financeapp.user.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("User not found!");
    }
    public NotFoundException(String message) {
        super(message);
    }
}

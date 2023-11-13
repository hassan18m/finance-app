package com.devhassan.financeapp.exceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException() {
    }

    public InvalidDataException(String message) {
        super(message);
    }
}

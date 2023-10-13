package com.devhassan.financeapp.exceptions;

public class NegativeBalanceException extends RuntimeException {
    public NegativeBalanceException(String message) {
        super(message);
    }

    public NegativeBalanceException() {
    }
}

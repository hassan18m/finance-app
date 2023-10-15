package com.devhassan.financeapp.exceptions;

public class NegativeBalanceException extends RuntimeException {
    public NegativeBalanceException() {
        super("Balance too low! Transaction cancelled.");
    }
}

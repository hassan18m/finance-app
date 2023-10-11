package com.devhassan.financeapp.bankaccount.controller;

import com.devhassan.financeapp.bankaccount.service.BankAccountService;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.user.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bank-account")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getBankAccountByAccountNumber(@PathVariable String accountNumber) {
        try {
            return ResponseEntity.ok(bankAccountService.getBankAccountByAccountNumber(accountNumber));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-transaction/{bankAccountId}")
    public ResponseEntity<?> addTransactionToBankAccount(@PathVariable Long bankAccountId, @RequestBody TransactionRequest transactionRequest) {
        try {
            return ResponseEntity.ok(bankAccountService.addTransactionToBankAccount(bankAccountId, transactionRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

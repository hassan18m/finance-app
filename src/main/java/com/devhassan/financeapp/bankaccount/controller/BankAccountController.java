package com.devhassan.financeapp.bankaccount.controller;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.bankaccount.service.BankAccountService;
import com.devhassan.financeapp.exceptions.NegativeBalanceException;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank-accounts")
public class BankAccountController {
    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public ResponseEntity<List<BankAccountResponse>> getAllBankAccounts() {
        return ResponseEntity.ok(bankAccountService.getAllBankAccounts());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> getBankAccountByAccountNumber(@PathVariable String accountNumber) {
        try {
            return ResponseEntity.ok(bankAccountService.getBankAccountByAccountNumber(accountNumber));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{bankAccountId}/transactions/expense")
    public ResponseEntity<?> addExpenseTransactionToBankAccount(@PathVariable Long bankAccountId, @RequestBody TransactionRequest transactionRequest) {
        try {
            transactionRequest.setTransactionType(TransactionType.EXPENSE);
            return ResponseEntity.ok(bankAccountService.addTransactionToBankAccount(bankAccountId, transactionRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NegativeBalanceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("{bankAccountId}/transactions/income")
    public ResponseEntity<?> addIncomeTransactionToBankAccount(@PathVariable Long bankAccountId, @RequestBody TransactionRequest transactionRequest) {
        try {
            transactionRequest.setTransactionType(TransactionType.INCOME);
            return ResponseEntity.ok(bankAccountService.addTransactionToBankAccount(bankAccountId, transactionRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

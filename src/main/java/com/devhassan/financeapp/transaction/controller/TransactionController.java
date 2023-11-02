package com.devhassan.financeapp.transaction.controller;

import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.transaction.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @DeleteMapping("/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long transactionId) {
        try {
            transactionService.deleteTransaction(transactionId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
    }
}

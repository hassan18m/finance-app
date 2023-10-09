package com.devhassan.financeapp.bankaccount.controller;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.bankaccount.helper.BankAccountInit;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bank-account")
public class BankAccountController {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountController(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    @PostMapping
    public ResponseEntity<BankAccount> insertBankAccount(@RequestBody BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = BankAccountInit.initBankAccountTest(bankAccountRequest);
        return ResponseEntity.ok(bankAccountRepository.save(bankAccount));
    }
}

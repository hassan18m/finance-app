package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.transaction.entity.Transaction;

public interface BankAccountService {
    BankAccountResponse getBankAccountByAccountNumber(String accountNumber);

    BankAccountResponse addTransactionToBankAccount(Long bankAccountId, Transaction transaction);
}

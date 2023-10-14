package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;

public interface BankAccountService {
    BankAccountResponse getBankAccountByAccountNumber(String accountNumber);

    BankAccountResponse addExpenseTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest);

    BankAccountResponse addIncomeTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest);

}

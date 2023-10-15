package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;

public interface BankAccountService {
    BankAccountResponse getBankAccountByAccountNumber(String accountNumber);

    BankAccountResponse addTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest);

}

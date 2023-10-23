package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;

import java.util.List;

public interface BankAccountService {
    BankAccountResponse getBankAccountByAccountNumber(String accountNumber);

    List<BankAccountResponse> getAllBankAccounts();

    BankAccountResponse addTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest);

}

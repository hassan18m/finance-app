package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;

import java.util.List;
import java.util.UUID;

public interface BankAccountService {
    BankAccountResponse getBankAccountByAccountNumber(String accountNumber);

    List<BankAccountResponse> getAllBankAccounts();
    List<BankAccountResponse> getUserBankAccounts(UUID userId);

    BankAccountResponse addTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest);
    void removeBankAccount(Long bankAccountId);
    void deleteBankAccountsFromUser(UUID userId);
}

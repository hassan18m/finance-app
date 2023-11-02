package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.transaction.entity.model.TransactionResponse;

import java.util.List;
import java.util.UUID;

public interface BankAccountService {
    BankAccountResponse getBankAccountByAccountNumber(String accountNumber);

    List<BankAccountResponse> getAllBankAccounts();
    List<BankAccountResponse> getUserBankAccounts(UUID userId);
    List<TransactionResponse> getBankAccountTransactions(Long bankAccountId);

    BankAccountResponse addTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest);
    void removeBankAccount(Long bankAccountId);
    void deleteBankAccountsFromUser(UUID userId);
}

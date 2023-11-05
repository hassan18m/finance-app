package com.devhassan.financeapp.transaction.service;

import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.transaction.entity.model.TransactionResponse;

public interface TransactionService {

    TransactionResponse updateTransaction(Long transactionId, TransactionRequest transactionRequest);
    void deleteTransaction(Long transactionId);
}

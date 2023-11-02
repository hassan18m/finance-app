package com.devhassan.financeapp.transaction.service;

import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public void deleteTransaction(Long transactionId) {
        Transaction foundTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found!"));

        transactionRepository.delete(foundTransaction);
    }
}

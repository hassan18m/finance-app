package com.devhassan.financeapp.transaction.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.devhassan.financeapp.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public void deleteTransaction(Long transactionId) {
        Transaction foundTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found!"));
        BankAccount transactionBankAccount = foundTransaction.getBankAccount();
        BigDecimal bankAccountBalance = transactionBankAccount.getBalance();

        if (foundTransaction.getTransactionType() == TransactionType.EXPENSE) {
            bankAccountBalance = bankAccountBalance.add(foundTransaction.getAmount());
            transactionBankAccount.setBalance(bankAccountBalance);
        } else if (foundTransaction.getTransactionType() == TransactionType.INCOME) {
            bankAccountBalance = bankAccountBalance.subtract(foundTransaction.getAmount());
            transactionBankAccount.setBalance(bankAccountBalance);
        }

        bankAccountRepository.save(transactionBankAccount);
        transactionRepository.delete(foundTransaction);
    }
}

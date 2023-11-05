package com.devhassan.financeapp.transaction.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.entity.enums.PaymentMethod;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.transaction.entity.model.TransactionResponse;
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
    public TransactionResponse updateTransaction(Long transactionId, TransactionRequest transactionRequest) {
        Transaction foundTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction not found!"));
        BankAccount bankAccountOfTransaction = getBankAccount(transactionRequest, foundTransaction);

        if (transactionRequest.getDescription() != null && !transactionRequest.getDescription().isBlank()) {
            foundTransaction.setDescription(transactionRequest.getDescription());
        }
        if (transactionRequest.getRecipient() != null && !transactionRequest.getRecipient().isBlank()) {
            foundTransaction.setRecipient(transactionRequest.getRecipient());
        }
        if (transactionRequest.getPaymentMethod() != null) {
            if (PaymentMethod.getPaymentMethods().contains(transactionRequest.getPaymentMethod())) {
                foundTransaction.setPaymentMethod(transactionRequest.getPaymentMethod());
            }
        }
        if (transactionRequest.getLocation() != null && !transactionRequest.getLocation().isBlank()) {
            foundTransaction.setLocation(transactionRequest.getLocation());
        }

        transactionRepository.save(foundTransaction);
        bankAccountRepository.save(bankAccountOfTransaction);
        return MapEntity.transactionEntityToResponse(foundTransaction);
    }

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

    private static BankAccount getBankAccount(TransactionRequest transactionRequest, Transaction foundTransaction) {
        BankAccount bankAccountOfTransaction = foundTransaction.getBankAccount();
        double bankAccountBalance = bankAccountOfTransaction.getBalance().doubleValue();

        if (transactionRequest.getAmount() != null && transactionRequest.getAmount().doubleValue() > 0) {
            if (foundTransaction.getTransactionType() == TransactionType.EXPENSE) {
                bankAccountBalance += foundTransaction.getAmount().doubleValue();
                bankAccountBalance -= transactionRequest.getAmount().doubleValue();
            }
            if (foundTransaction.getTransactionType() == TransactionType.INCOME) {
                bankAccountBalance -= foundTransaction.getAmount().doubleValue();
                bankAccountBalance += transactionRequest.getAmount().doubleValue();
            }
            foundTransaction.setAmount(transactionRequest.getAmount());
            bankAccountOfTransaction.setBalance(BigDecimal.valueOf(bankAccountBalance));
        }
        return bankAccountOfTransaction;
    }
}

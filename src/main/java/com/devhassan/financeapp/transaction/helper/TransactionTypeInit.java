/*
package com.devhassan.financeapp.transaction.helper;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;


import java.time.LocalDateTime;
import java.util.Set;

public class TransactionTypeInit {

    public static Transaction initExpenseTransaction(BankAccount bankAccount, TransactionRequest transactionRequest) {
            Transaction transaction = MapEntity.transactionRequestToEntity(transactionRequest);
            transaction.setTransactionDateTime(LocalDateTime.now());

            Set<Transaction> bankAccountsTransactions = bankAccount.getTransactions();
            bankAccountsTransactions.add(transaction);
    }
}*/

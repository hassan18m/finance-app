package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.expensecategory.entity.ExpenseCategory;
import com.devhassan.financeapp.expensecategory.repository.ExpenseCategoryRepository;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.transaction.repository.TransactionRepository;
import com.devhassan.financeapp.user.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  TransactionRepository transactionRepository,
                                  ExpenseCategoryRepository expenseCategoryRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Override
    public BankAccountResponse getBankAccountByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .map(MapEntity::bankAccountEntityToResponse)
                .orElseThrow(() -> new NotFoundException("Account with number: " + accountNumber + " not found!"));
    }

    @Override
    public BankAccountResponse addTransactionToBankAccount(Long bankAccountId,
                                                           TransactionRequest transactionRequest) {
        BankAccount foundBankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank account not found!"));

        Transaction transaction = MapEntity.transactionRequestToEntity(transactionRequest);
        transaction.setTransactionDateTime(LocalDateTime.now());

        Set<Transaction> bankAccountsTransactions = foundBankAccount.getTransactions();
        bankAccountsTransactions.add(transaction);

        if (transaction.getTransactionType() == TransactionType.EXPENSE) {
            foundBankAccount.setBalance(foundBankAccount.getBalance().subtract(transaction.getAmount()));
        } else if (transaction.getTransactionType() == TransactionType.INCOME) {
            foundBankAccount.setBalance(foundBankAccount.getBalance().add(transaction.getAmount()));
        }

        foundBankAccount.setTransactions(bankAccountsTransactions);
        transaction.setBankAccount(foundBankAccount);

        expenseCategoryRepository.save(transaction.getExpenseCategory());
        transactionRepository.save(transaction);
        bankAccountRepository.save(foundBankAccount);

        return MapEntity.bankAccountEntityToResponse(foundBankAccount);
    }
}

package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.expensecategory.repository.ExpenseCategoryRepository;
import com.devhassan.financeapp.financialInsight.service.FinancialInsightService;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.transaction.repository.TransactionRepository;
import com.devhassan.financeapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final FinancialInsightService financialInsightService;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  TransactionRepository transactionRepository,
                                  ExpenseCategoryRepository expenseCategoryRepository,
                                  FinancialInsightService financialInsightService) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.financialInsightService = financialInsightService;
    }

    @Override
    public BankAccountResponse getBankAccountByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .map(MapEntity::bankAccountEntityToResponse)
                .orElseThrow(() -> new NotFoundException("Account with number: " + accountNumber + " not found!"));
    }

    @Override
    public BankAccountResponse addExpenseTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest) {
        BankAccount foundBankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank account not found!"));

        Set<Transaction> bankAccountTransactions = foundBankAccount.getTransactions();
        Transaction transaction = MapEntity.expenseTransactionRequestToEntity(transactionRequest);
        bankAccountTransactions.add(transaction);
        foundBankAccount.setTransactions(bankAccountTransactions);

        foundBankAccount.setBalance(foundBankAccount.getBalance().subtract(transaction.getAmount()));

        transaction.setBankAccount(foundBankAccount);

        expenseCategoryRepository.save(transaction.getExpenseCategory());
        transactionRepository.save(transaction);
        bankAccountRepository.save(foundBankAccount);

        return MapEntity.bankAccountEntityToResponse(foundBankAccount);
    }

    @Override
    public BankAccountResponse addIncomeTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest) {
        BankAccount foundBankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank account not found!"));

        Set<Transaction> bankAccountTransactions = foundBankAccount.getTransactions();
        Transaction transaction = MapEntity.incomeTransactionRequestToEntity(transactionRequest);
        bankAccountTransactions.add(transaction);
        foundBankAccount.setTransactions(bankAccountTransactions);
        transaction.setBankAccount(foundBankAccount);

        transactionRepository.save(transaction);
        bankAccountRepository.save(foundBankAccount);

        return MapEntity.bankAccountEntityToResponse(foundBankAccount);
    }
}

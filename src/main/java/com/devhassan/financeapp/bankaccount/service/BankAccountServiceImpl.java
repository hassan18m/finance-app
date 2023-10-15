package com.devhassan.financeapp.bankaccount.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.exceptions.NegativeBalanceException;
import com.devhassan.financeapp.expensecategory.repository.ExpenseCategoryRepository;
import com.devhassan.financeapp.financialInsight.service.FinancialInsightService;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.transaction.repository.TransactionRepository;
import com.devhassan.financeapp.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final FinancialInsightService financialInsightService;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  TransactionRepository transactionRepository,
                                  FinancialInsightService financialInsightService,
                                  ExpenseCategoryRepository expenseCategoryRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.financialInsightService = financialInsightService;
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Override
    public BankAccountResponse getBankAccountByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .map(MapEntity::bankAccountEntityToResponse)
                .orElseThrow(() -> new NotFoundException("Account with number: " + accountNumber + " not found!"));
    }

    // TODO: 16-Oct-23 code cleaning.
    @Override
    public BankAccountResponse addTransactionToBankAccount(Long bankAccountId, TransactionRequest transactionRequest) {
        BankAccount foundBankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank account not found!"));

        Transaction transaction = MapEntity.transactionRequestToEntity(transactionRequest);
        transaction.setBankAccount(foundBankAccount);

        boolean isExpenseTransaction = transaction.getTransactionType() == TransactionType.EXPENSE;
        boolean isIncomeTransaction = transaction.getTransactionType() == TransactionType.INCOME;
        double transactionAmount = transaction.getAmount().doubleValue();
        double remainingBalance = foundBankAccount.getBalance().doubleValue();

        if (isExpenseTransaction) {
            remainingBalance -= transactionAmount;
            if (remainingBalance < 0) {
                throw new NegativeBalanceException();
            }
            foundBankAccount.setBalance(BigDecimal.valueOf(remainingBalance));
            if (remainingBalance < 500) {
                financialInsightService.generateInsight(foundBankAccount.getUser());
            }
            if (transaction.getExpenseCategory() != null) {
                expenseCategoryRepository.save(transaction.getExpenseCategory());
            }
        }

        if (isIncomeTransaction) {
            remainingBalance += transactionAmount;
            foundBankAccount.setBalance(BigDecimal.valueOf(remainingBalance));
        }

        transactionRepository.save(transaction);
        bankAccountRepository.save(foundBankAccount);

        return MapEntity.bankAccountEntityToResponse(foundBankAccount);
    }


}

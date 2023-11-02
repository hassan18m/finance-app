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
import com.devhassan.financeapp.transaction.entity.model.TransactionResponse;
import com.devhassan.financeapp.transaction.repository.TransactionRepository;
import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final FinancialInsightService financialInsightService;
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository,
                                  TransactionRepository transactionRepository,
                                  FinancialInsightService financialInsightService,
                                  ExpenseCategoryRepository expenseCategoryRepository,
                                  UserRepository userRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.financialInsightService = financialInsightService;
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BankAccountResponse getBankAccountByAccountNumber(String accountNumber) {
        return bankAccountRepository.findByAccountNumber(accountNumber)
                .map(MapEntity::bankAccountEntityToResponse)
                .orElseThrow(() -> new NotFoundException("Account with number: " + accountNumber + " not found!"));
    }

    @Override
    public List<BankAccountResponse> getAllBankAccounts() {
        return bankAccountRepository.findAll()
                .stream()
                .map(MapEntity::bankAccountEntityToResponse)
                .toList();
    }

    @Override
    public List<BankAccountResponse> getUserBankAccounts(UUID userId) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundException();
        }

        return bankAccountRepository.findBankAccountsByUser_Id(userId)
                .stream()
                .map(MapEntity::bankAccountEntityToResponse)
                .toList();
    }

    @Override
    public List<TransactionResponse> getBankAccountTransactions(Long bankAccountId) {
        BankAccount foundBankAccount = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank account not found!"));

        return foundBankAccount.getTransactions()
                .stream()
                .map(MapEntity::transactionEntityToResponse)
                .toList();
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

    @Override
    public void removeBankAccount(Long bankAccountId) {
        BankAccount bankAccountToDelete = bankAccountRepository.findById(bankAccountId)
                .orElseThrow(() -> new NotFoundException("Bank Account not found"));

        bankAccountRepository.delete(bankAccountToDelete);
    }

    @Override
    public void deleteBankAccountsFromUser(UUID userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        List<BankAccount> usersBankAccounts = foundUser.getBankAccounts().stream().toList();

        bankAccountRepository.deleteAll(usersBankAccounts);
    }
}

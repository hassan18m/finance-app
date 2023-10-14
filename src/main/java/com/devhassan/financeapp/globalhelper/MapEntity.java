package com.devhassan.financeapp.globalhelper;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.budget.entity.Budget;
import com.devhassan.financeapp.budget.entity.model.BudgetRequest;
import com.devhassan.financeapp.budget.entity.model.BudgetResponse;
import com.devhassan.financeapp.expensecategory.entity.ExpenseCategory;
import com.devhassan.financeapp.expensecategory.entity.model.ExpenseCategoryResponse;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.devhassan.financeapp.transaction.entity.model.TransactionRequest;
import com.devhassan.financeapp.transaction.entity.model.TransactionResponse;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class MapEntity {
    public static User userRequestToEntity(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

        return user;
    }

    public static UserResponse userEntityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setBankAccounts(user.getBankAccounts()
                .stream()
                .map(MapEntity::bankAccountEntityToResponse)
                .collect(Collectors.toSet()));
        userResponse.setBudgets(user.getBudgets()
                .stream()
                .map(MapEntity::budgetEntityToResponse)
                .collect(Collectors.toSet()));
        userResponse.setFinancialInsights(user.getFinancialInsights().stream().toList());

        return userResponse;
    }

    public static BankAccountResponse bankAccountEntityToResponse(BankAccount bankAccount) {

        BankAccountResponse bankAccountResponse = new BankAccountResponse();
        bankAccountResponse.setId(bankAccount.getId());
        bankAccountResponse.setAccountNumber(bankAccount.getAccountNumber());
        bankAccountResponse.setAccountHolderName(bankAccount.getAccountHolderName());
        bankAccountResponse.setBankName(bankAccount.getBankName());
        bankAccountResponse.setAccountType(bankAccount.getAccountType());
        bankAccountResponse.setBalance(bankAccount.getBalance());
        bankAccountResponse.setCurrency(bankAccount.getCurrency());
        bankAccountResponse.setOpenDate(bankAccount.getOpenDate());
        bankAccountResponse.setClosedDate(bankAccount.getClosedDate());
        bankAccountResponse.setStatus(bankAccount.getStatus());
        bankAccountResponse.setTransactions(bankAccount.getTransactions()
                .stream()
                .map(MapEntity::transactionEntityToResponse)
                .collect(Collectors.toSet()));

        return bankAccountResponse;
    }

    public static TransactionResponse transactionEntityToResponse(Transaction transaction) {
        if (transaction.getTransactionType() == TransactionType.EXPENSE) {
            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setId(transaction.getId());
            transactionResponse.setAmount(transaction.getAmount());
            transactionResponse.setTransactionDateTime(transaction.getTransactionDateTime());
            transactionResponse.setTransactionType(transaction.getTransactionType());
            transactionResponse.setDescription(transaction.getDescription());
            transactionResponse.setExpenseCategory(MapEntity
                    .transactionExpenseCategoryEntityToResponse(transaction.getExpenseCategory()));
            transactionResponse.setRecipient(transaction.getRecipient());
            transactionResponse.setPaymentMethod(transaction.getPaymentMethod());
            transactionResponse.setLocation(transaction.getLocation());

            return transactionResponse;
        }

        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getTransactionDateTime(),
                transaction.getTransactionType(),
                transaction.getDescription(),
                transaction.getRecipient(),
                transaction.getPaymentMethod(),
                transaction.getLocation()
        );
    }

    public static Transaction expenseTransactionRequestToEntity(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();
        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setCategoryName(transactionRequest.getCategoryName());

        transaction.setAmount(transactionRequest.getAmount());
        transaction.setTransactionDateTime(LocalDateTime.now());
        transaction.setTransactionType(TransactionType.EXPENSE);
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setExpenseCategory(expenseCategory);
        transaction.setRecipient(transactionRequest.getRecipient());
        transaction.setPaymentMethod(transactionRequest.getPaymentMethod());
        transaction.setLocation(transactionRequest.getLocation());

        return transaction;
    }

    public static Transaction incomeTransactionRequestToEntity(TransactionRequest transactionRequest) {
        return new Transaction(
                transactionRequest.getAmount(),
                LocalDateTime.now(),
                TransactionType.INCOME,
                transactionRequest.getDescription(),
                transactionRequest.getRecipient(),
                transactionRequest.getPaymentMethod(),
                transactionRequest.getLocation()
        );
    }

    public static Budget budgetRequestToEntity(BudgetRequest budgetRequest) {
        Budget budget = new Budget();
        budget.setAmount(budgetRequest.getAmount());
        budget.setStartDate(budgetRequest.getStartDate());
        budget.setEndDate(budgetRequest.getEndDate());

        return budget;
    }

    public static BudgetResponse budgetEntityToResponse(Budget budget) {
        BudgetResponse budgetResponse = new BudgetResponse();
        budgetResponse.setId(budget.getId());
        budgetResponse.setAmount(budget.getAmount());
        budgetResponse.setStartDate(budget.getStartDate());
        budgetResponse.setEndDate(budget.getEndDate());
        budgetResponse.setExpenseCategories(budget.getExpenseCategories()
                .stream()
                .map(MapEntity::budgetExpenseCategoryEntityToResponse)
                .collect(Collectors.toSet()));

        return budgetResponse;
    }

    private static ExpenseCategoryResponse transactionExpenseCategoryEntityToResponse(ExpenseCategory expenseCategory) {
        ExpenseCategoryResponse expenseCategoryResponse = new ExpenseCategoryResponse();
        expenseCategoryResponse.setCategoryName(expenseCategory.getCategoryName());
        expenseCategoryResponse.setBudgets(expenseCategory.getBudgets()
                .stream()
                .map(MapEntity::budgetEntityToResponse)
                .peek(budgetResponse -> {
                    //Getting only id and amount fields for transaction expenseCategory.
                    budgetResponse.setExpenseCategories(null);
                    budgetResponse.setEndDate(null);
                    budgetResponse.setStartDate(null);
                })
                .collect(Collectors.toSet()));

        return expenseCategoryResponse;
    }

    private static ExpenseCategoryResponse budgetExpenseCategoryEntityToResponse(ExpenseCategory expenseCategory) {
        ExpenseCategoryResponse expenseCategoryResponse = new ExpenseCategoryResponse();
        expenseCategoryResponse.setCategoryName(expenseCategory.getCategoryName());

        return expenseCategoryResponse;
    }
}
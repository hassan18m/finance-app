package com.devhassan.financeapp.user.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.budget.entity.model.BudgetRequest;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponse> findAllUsers();

    UserResponse insertUser(UserRequest userRequest);

    UserResponse findByEmail(String email);

    UserResponse findById(UUID id);

    BigDecimal getTotalBalance(UUID userId);

    UserResponse addBankAccount(UUID userId, BankAccountRequest bankAccountRequest);

    UserResponse setBudget(UUID userId, BudgetRequest budgetRequest);

    List<UserResponse> populateWithUsers();
}

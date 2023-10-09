package com.devhassan.financeapp.user.service;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse insertUser(UserRequest userRequest);

    UserResponse findByEmail(String email);

    UserResponse findById(UUID id);

    UserResponse addBankAccount(UUID userId, BankAccountRequest bankAccountRequest);
}

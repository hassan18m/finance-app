package com.devhassan.financeapp.user.entity.model;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<BankAccount> bankAccounts;
}

package com.devhassan.financeapp.bankaccount.entity.model;

import com.devhassan.financeapp.bankaccount.entity.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountRequest {
    private String bankName;
    private AccountType accountType;
    private String currency;
}

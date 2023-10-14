package com.devhassan.financeapp.bankaccount.helper;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.enums.AccountStatus;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;

public class BankAccountInit {
    private static Long randomNumber = 1115L;

    public static BankAccount initBankAccount(User user, BankAccountRequest bankAccountRequest) {
        BankAccount bankAccount = BankAccount.builder()
                .accountNumber("RO" + randomNumber.toString())
                .bankName(bankAccountRequest.getBankName())
                .accountHolderName(user.getFirstName() + " " + user.getLastName())
                .accountType(bankAccountRequest.getAccountType())
                .balance(BigDecimal.valueOf(100.00))
                .currency(bankAccountRequest.getCurrency())
                .openDate(LocalDateTime.now())
                .closedDate(null)
                .status(AccountStatus.ACTIVE)
                .transactions(new HashSet<>())
                .build();
        randomNumber++;

        return bankAccount;
    }
}

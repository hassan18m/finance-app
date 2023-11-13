package com.devhassan.financeapp.bankaccount.helper;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.enums.AccountStatus;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.user.entity.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

// TODO: 16-17-Oct-23 - implement a better logic for BankAccountInit.

public class BankAccountInit {

    private static Integer randomNumber = (int) (Math.random() * 10000);

    public static BankAccount initBankAccount(User user, BankAccountRequest bankAccountRequest, List<String> accountNumbers) {
        if (accountNumbers.contains(randomNumber.toString())) {
            randomNumber += 10;
        }
        randomNumber++;

        return BankAccount.builder()
                .accountNumber("RO" + randomNumber)
                .bankName(bankAccountRequest.getBankName())
                .accountHolderName(user.getFirstName() + " " + user.getLastName())
                .accountType(bankAccountRequest.getAccountType())
                .balance(BigDecimal.valueOf(100.00))
                .currency(bankAccountRequest.getCurrency())
                .openDate(LocalDateTime.now())
                .closedDate(null)
                .status(AccountStatus.ACTIVE)
                .transactions(new HashSet<>())
                .budgets(new HashSet<>())
                .build();
    }
}

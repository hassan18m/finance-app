package com.devhassan.financeapp.authentication;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class Statistics {
    private int noOfUsers;
    private double moneyInCirculation;
    private int noOfBankAccounts;
    private long noOfTransactions;
    private String mostUsedBank;
}

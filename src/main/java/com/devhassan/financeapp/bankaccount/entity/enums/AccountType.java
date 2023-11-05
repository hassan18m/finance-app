package com.devhassan.financeapp.bankaccount.entity.enums;

import java.util.ArrayList;
import java.util.List;

public enum AccountType {

    CHECKING_ACCOUNT,
    SAVINGS_ACCOUNT;

    public static List<AccountType> getAccountTypes() {
        List<AccountType> accountTypeList = new ArrayList<>();
        accountTypeList.add(CHECKING_ACCOUNT);
        accountTypeList.add(SAVINGS_ACCOUNT);

        return accountTypeList;
    }
}
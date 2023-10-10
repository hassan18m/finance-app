package com.devhassan.financeapp.globalhelper;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountResponse;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;

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
        userResponse.setBankAccounts(user.getBankAccounts());

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
        bankAccountResponse.setTransactions(bankAccount.getTransactions());

        return bankAccountResponse;
    }
}

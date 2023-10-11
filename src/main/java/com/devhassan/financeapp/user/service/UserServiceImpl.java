package com.devhassan.financeapp.user.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.bankaccount.helper.BankAccountInit;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.budget.entity.Budget;
import com.devhassan.financeapp.budget.repository.BudgetRepository;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;
import com.devhassan.financeapp.user.exceptions.DuplicateDataException;
import com.devhassan.financeapp.user.exceptions.NotFoundException;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BudgetRepository budgetRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BankAccountRepository bankAccountRepository,
                           BudgetRepository budgetRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.budgetRepository = budgetRepository;
    }

    @Override
    public UserResponse insertUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new DuplicateDataException("Email already used!");
        }

        User user = MapEntity.userRequestToEntity(userRequest);
        userRepository.save(user);

        return MapEntity.userEntityToResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(MapEntity::userEntityToResponse)
                .orElseThrow(() -> new NotFoundException("Email not found!"));
    }

    @Override
    public UserResponse findById(UUID id) {
        return userRepository.findById(id)
                .map(MapEntity::userEntityToResponse)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public UserResponse addBankAccount(UUID userId, BankAccountRequest bankAccountRequest) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        BankAccount bankAccount = BankAccountInit.initBankAccount(foundUser, bankAccountRequest);

        Set<BankAccount> bankAccountsOwnedByUser = foundUser.getBankAccounts();
        bankAccountsOwnedByUser.add(bankAccount);
        foundUser.setBankAccounts(bankAccountsOwnedByUser);

        bankAccount.setUser(foundUser);
        bankAccountRepository.save(bankAccount);
        userRepository.save(foundUser);


        return MapEntity.userEntityToResponse(foundUser);
    }

    @Override
    public UserResponse setBudget(UUID userId, Budget budget) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        Set<Budget> userBudgets = foundUser.getBudgets();
        userBudgets.add(budget);

        budget.setUser(foundUser);

        budgetRepository.save(budget);
        userRepository.save(foundUser);

        return MapEntity.userEntityToResponse(foundUser);
    }
}

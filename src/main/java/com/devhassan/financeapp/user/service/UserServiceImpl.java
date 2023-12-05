package com.devhassan.financeapp.user.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.bankaccount.helper.BankAccountInit;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.budget.entity.Budget;
import com.devhassan.financeapp.budget.entity.model.BudgetRequest;
import com.devhassan.financeapp.budget.repository.BudgetRepository;
import com.devhassan.financeapp.exceptions.InvalidDataException;
import com.devhassan.financeapp.expensecategory.entity.ExpenseCategory;
import com.devhassan.financeapp.expensecategory.repository.ExpenseCategoryRepository;
import com.devhassan.financeapp.globalhelper.Converter;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;
import com.devhassan.financeapp.exceptions.DuplicateDataException;
import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(MapEntity::userEntityToResponse)
                .toList();
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
    public BigDecimal getTotalBalance(UUID userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        List<BankAccount> userBankAccounts = foundUser.getBankAccounts().stream().toList();

        double totalBalance = userBankAccounts
                .stream()
                .mapToDouble(bankAccount -> {
                    double bankAccountBalance = bankAccount.getBalance().doubleValue();
                    return Converter.convertToRon(bankAccountBalance, bankAccount.getCurrency());
                })
                .sum();

        return BigDecimal.valueOf(totalBalance);
    }

    @Override
    public UserResponse addBankAccount(UUID userId, BankAccountRequest bankAccountRequest) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        BankAccount bankAccount = BankAccountInit.initBankAccount(foundUser, bankAccountRequest, getAccountNumbers());

        foundUser.getBankAccounts().add(bankAccount);
        bankAccount.setUser(foundUser);
        bankAccountRepository.save(bankAccount);
        userRepository.save(foundUser);

        return MapEntity.userEntityToResponse(foundUser);
    }

    @Override
    public UserResponse setBudget(UUID userId, BudgetRequest budgetRequest) {
        User foundUser = userRepository.findById(userId).orElseThrow(NotFoundException::new);

        Budget budget = MapEntity.budgetRequestToEntity(budgetRequest);
        setExpenseCategoriesToBudget(budget, budgetRequest);

        if (!isBankAccountValid(foundUser, budgetRequest)) {
            throw new InvalidDataException("Bank account not valid!");
        }
        BankAccount budgetBankAccount = bankAccountRepository.getReferenceById(budgetRequest.getBankId());
        budgetBankAccountOperations(budgetBankAccount, budget);

        budget.setBankAccount(budgetBankAccount);
        foundUser.getBudgets().add(budget);
        budget.setUser(foundUser);
        budgetBankAccount.getBudgets().add(budget);

        budgetRepository.save(budget);
        bankAccountRepository.save(budgetBankAccount);
        userRepository.save(foundUser);

        return MapEntity.userEntityToResponse(foundUser);
    }

    @Override
    public List<UserResponse> populateWithUsers() {
        List<User> usersList = new ArrayList<>();

        for (int i = 1; i <= 20; i++) {
            User user = new User();
            user.setFirstName("testFirstName_" + i);
            user.setLastName("testLastName_" + i);
            user.setEmail("testEmail_" + i + "@test_" + i + ".com");
            user.setPassword("password_" + i);

            userRepository.save(user);
            usersList.add(user);
        }

        return usersList
                .stream()
                .map(MapEntity::userEntityToResponse)
                .toList();
    }

    private void budgetBankAccountOperations(BankAccount bankAccount, Budget budget) {
        if (budget.getAmount().doubleValue() > bankAccount.getBalance().doubleValue()) {
            throw new InvalidDataException("Budget amount cannot be more then your balance!");
        }

        BigDecimal newBankAccountBalance = bankAccount.getBalance().subtract(budget.getAmount());
        bankAccount.setBalance(newBankAccountBalance);
    }

    private boolean isBankAccountValid(User user, BudgetRequest budgetRequest) {
        List<BankAccount> userBankAccounts = user.getBankAccounts().stream().toList();
        Optional<BankAccount> budgetRequestBankAccount = bankAccountRepository.findById(budgetRequest.getBankId());

        return budgetRequestBankAccount
                .filter(userBankAccounts::contains)
                .isPresent();
    }

    private void setExpenseCategoriesToBudget(Budget budget, BudgetRequest budgetRequest) {
        Set<ExpenseCategory> expenseCategoriesForBudget = new HashSet<>();

        budgetRequest.getExpenseCategories().forEach(categoryName -> {
            ExpenseCategory expenseCategory = expenseCategoryRepository.findByCategoryName(categoryName)
                    .orElseThrow(() -> new NotFoundException("Expense Categories non existent!"));
            expenseCategoriesForBudget.add(expenseCategory);
        });

        budget.setExpenseCategories(expenseCategoriesForBudget);
    }


    private List<String> getAccountNumbers() {
        return bankAccountRepository.findAll()
                .stream()
                .map(BankAccount::getAccountNumber)
                .toList();
    }
}

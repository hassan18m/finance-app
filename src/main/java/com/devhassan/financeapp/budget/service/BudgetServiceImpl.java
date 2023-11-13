package com.devhassan.financeapp.budget.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.budget.entity.Budget;
import com.devhassan.financeapp.budget.entity.model.BudgetRequest;
import com.devhassan.financeapp.budget.entity.model.BudgetResponse;
import com.devhassan.financeapp.budget.entity.model.UpdateBudgetRequest;
import com.devhassan.financeapp.budget.repository.BudgetRepository;
import com.devhassan.financeapp.exceptions.InvalidDataException;
import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.globalhelper.MapEntity;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService {
    private final UserRepository userRepository;
    private final BudgetRepository budgetRepository;
    private final BankAccountRepository bankAccountRepository;


    @Override
    public List<BudgetResponse> getUserBudgets(UUID userId) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        return foundUser.getBudgets()
                .stream()
                .map(MapEntity::budgetEntityToResponse)
                .toList();
    }

    @Override
    public void removeBudget(Long budgetId) {
        Budget foundBudget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new NotFoundException("Budget not found!"));

        BankAccount budgetBankAccount = foundBudget.getBankAccount();
        BigDecimal newBankAccountBalance = budgetBankAccount.getBalance().add(foundBudget.getAmount());
        budgetBankAccount.setBalance(newBankAccountBalance);

        bankAccountRepository.save(budgetBankAccount);
        budgetRepository.delete(foundBudget);
    }

    @Override
    public BudgetResponse updateBudget(Long budgetId, UpdateBudgetRequest budgetRequest) {
        Budget foundBudget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new NotFoundException("Budget not found!"));
        BankAccount budgetBankAccount = foundBudget.getBankAccount();

        if (budgetRequest.getAmount() != null && budgetRequest.getAmount().doubleValue() > 0) {
            BigDecimal initialBankAccountBalance = budgetBankAccount.getBalance().add(foundBudget.getAmount());
            budgetBankAccount.setBalance(initialBankAccountBalance);
            if (budgetRequest.getAmount().doubleValue() > budgetBankAccount.getBalance().doubleValue()) {
                throw new InvalidDataException("Budget amount cannot be greater than Bank Account balance!");
            }

            BigDecimal newBankAccountBalance = budgetBankAccount.getBalance().subtract(budgetRequest.getAmount());
            budgetBankAccount.setBalance(newBankAccountBalance);

            foundBudget.setAmount(budgetRequest.getAmount());
            budgetRepository.save(foundBudget);
            bankAccountRepository.save(budgetBankAccount);
        }

        if (budgetRequest.getName() != null && !budgetRequest.getName().isBlank()) {
            foundBudget.setName(budgetRequest.getName());
            budgetRepository.save(foundBudget);
        }

        return MapEntity.budgetEntityToResponse(foundBudget);
    }
}

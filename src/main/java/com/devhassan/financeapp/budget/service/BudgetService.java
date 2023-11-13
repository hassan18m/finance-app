package com.devhassan.financeapp.budget.service;

import com.devhassan.financeapp.budget.entity.model.BudgetRequest;
import com.devhassan.financeapp.budget.entity.model.BudgetResponse;
import com.devhassan.financeapp.budget.entity.model.UpdateBudgetRequest;

import java.util.List;
import java.util.UUID;

public interface BudgetService {
    List<BudgetResponse> getUserBudgets(UUID userId);

    void removeBudget(Long budgetId);

    BudgetResponse updateBudget(Long budgetId, UpdateBudgetRequest budgetRequest);
}

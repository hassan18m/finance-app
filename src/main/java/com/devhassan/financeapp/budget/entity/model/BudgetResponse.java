package com.devhassan.financeapp.budget.entity.model;

import com.devhassan.financeapp.expensecategory.entity.model.ExpenseCategoryResponseForBudget;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BudgetResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    Set<ExpenseCategoryResponseForBudget> expenseCategories = new HashSet<>();
}


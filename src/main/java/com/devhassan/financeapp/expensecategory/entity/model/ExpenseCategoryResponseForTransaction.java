package com.devhassan.financeapp.expensecategory.entity.model;

import com.devhassan.financeapp.budget.entity.model.BudgetResponseForExpenseCategory;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExpenseCategoryResponseForTransaction {
    private String categoryName;
    private Set<BudgetResponseForExpenseCategory> budgetResponse;
}



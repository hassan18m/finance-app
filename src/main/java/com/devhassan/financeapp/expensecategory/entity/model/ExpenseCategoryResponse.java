package com.devhassan.financeapp.expensecategory.entity.model;

import com.devhassan.financeapp.budget.entity.model.BudgetResponse;
import com.devhassan.financeapp.expensecategory.entity.enums.CategoryName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExpenseCategoryResponse {
    private CategoryName categoryName;
    private Set<BudgetResponse> budgets;
}



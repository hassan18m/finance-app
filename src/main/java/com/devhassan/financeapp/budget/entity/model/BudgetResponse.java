package com.devhassan.financeapp.budget.entity.model;

import com.devhassan.financeapp.expensecategory.entity.model.ExpenseCategoryResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    Set<ExpenseCategoryResponse> expenseCategories;
}


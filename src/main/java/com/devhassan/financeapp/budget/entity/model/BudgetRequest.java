package com.devhassan.financeapp.budget.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetRequest {
    private BigDecimal amount;
    private LocalDate startDate;
    private LocalDate endDate;
    List<String> expenseCategories;
}

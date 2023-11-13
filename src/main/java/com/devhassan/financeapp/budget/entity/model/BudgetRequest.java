package com.devhassan.financeapp.budget.entity.model;

import com.devhassan.financeapp.expensecategory.entity.enums.CategoryName;
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
    private String name;
    private BigDecimal amount;
    private Long bankId;
    private LocalDate startDate;
    private LocalDate endDate;
    List<CategoryName> expenseCategories;
}

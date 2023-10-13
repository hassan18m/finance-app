package com.devhassan.financeapp.budget.entity.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BudgetResponseForExpenseCategory {
    private Long id;
    private BigDecimal amount;
}


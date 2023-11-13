package com.devhassan.financeapp.budget.entity.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBudgetRequest {
    private String name;
    private BigDecimal amount;
}

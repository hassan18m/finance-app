package com.devhassan.financeapp.expensecategory.entity.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExpenseCategoryResponseForBudget {
    private String categoryName;
}
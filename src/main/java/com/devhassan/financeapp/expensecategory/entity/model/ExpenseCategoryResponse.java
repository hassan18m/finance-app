package com.devhassan.financeapp.expensecategory.entity.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExpenseCategoryResponse {
    private Long id;
    private String categoryName;
}



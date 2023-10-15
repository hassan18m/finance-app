package com.devhassan.financeapp.transaction.entity.model;

import com.devhassan.financeapp.expensecategory.entity.model.ExpenseCategoryResponse;
import com.devhassan.financeapp.transaction.entity.enums.PaymentMethod;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime transactionDateTime;
    private TransactionType transactionType;
    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExpenseCategoryResponse expenseCategory;

    private String recipient;
    private PaymentMethod paymentMethod;
    private String location;
}

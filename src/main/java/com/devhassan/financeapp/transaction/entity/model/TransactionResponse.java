package com.devhassan.financeapp.transaction.entity.model;

import com.devhassan.financeapp.expensecategory.entity.model.ExpenseCategoryResponseForTransaction;
import com.devhassan.financeapp.transaction.entity.enums.PaymentMethod;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionResponse {
    private Long id;
    private BigDecimal amount;
    private LocalDateTime transactionDateTime;
    private TransactionType transactionType;
    private String description;
    private ExpenseCategoryResponseForTransaction expenseCategory;
    private String recipient;
    private PaymentMethod paymentMethod;
    private String location;
}

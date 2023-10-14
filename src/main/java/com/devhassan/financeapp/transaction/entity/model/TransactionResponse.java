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
    //For income transactions
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ExpenseCategoryResponse expenseCategory;

    private String recipient;
    private PaymentMethod paymentMethod;
    private String location;

    public TransactionResponse(Long id, BigDecimal amount, LocalDateTime transactionDateTime,
                               TransactionType transactionType, String description,
                               String recipient, PaymentMethod paymentMethod, String location) {
        this.id = id;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
        this.transactionType = transactionType;
        this.description = description;
        this.recipient = recipient;
        this.paymentMethod = paymentMethod;
        this.location = location;
    }
}

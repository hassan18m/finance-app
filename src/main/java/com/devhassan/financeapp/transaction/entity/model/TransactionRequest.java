package com.devhassan.financeapp.transaction.entity.model;

import com.devhassan.financeapp.transaction.entity.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransactionRequest {
    private BigDecimal amount;
    private String description;
    private String categoryName;
    private String recipient;
    private PaymentMethod paymentMethod;
    private String location;
}
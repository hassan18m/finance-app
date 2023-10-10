package com.devhassan.financeapp.transaction.entity;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.transaction.entity.enums.PaymentMethod;
import com.devhassan.financeapp.transaction.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date_time")
    private LocalDateTime transactionDateTime;

    @Column(name = "transaction_type")
    @Enumerated(value = EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "description")
    private String description;

    @Column(name = "expense_category")
    private String expenseCategory;

    @Column(name = "recipient")
    private String recipient;

    @Column(name = "payment_method")
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Column(name = "location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "bank_account_id", nullable = false)
    @JsonIgnore
    private BankAccount bankAccount;
}

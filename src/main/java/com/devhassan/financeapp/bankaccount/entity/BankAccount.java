package com.devhassan.financeapp.bankaccount.entity;

import com.devhassan.financeapp.bankaccount.entity.enums.AccountStatus;
import com.devhassan.financeapp.bankaccount.entity.enums.AccountType;
import com.devhassan.financeapp.transaction.entity.Transaction;
import com.devhassan.financeapp.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity(name = "bank_account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "bank_name")
    private String bankName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private String currency;

    @Column(name = "open_date")
    private LocalDateTime openDate;

    @Column(name = "closed_date")
    private LocalDateTime closedDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AccountStatus status;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "bankAccount")
    private Set<Transaction> transactions;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BankAccount account = (BankAccount) obj;
        return id != null && id.equals(account.id);
    }
}

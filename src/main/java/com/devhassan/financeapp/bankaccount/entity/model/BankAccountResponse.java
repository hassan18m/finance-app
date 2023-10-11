package com.devhassan.financeapp.bankaccount.entity.model;

import com.devhassan.financeapp.bankaccount.entity.enums.AccountStatus;
import com.devhassan.financeapp.bankaccount.entity.enums.AccountType;
import com.devhassan.financeapp.transaction.entity.model.TransactionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankAccountResponse {
    private Long id;
    private String accountNumber;
    private String accountHolderName;
    private String bankName;
    private AccountType accountType;
    private BigDecimal balance;
    private String currency;
    private LocalDateTime openDate;
    private LocalDateTime closedDate;
    private AccountStatus status;
    private Set<TransactionResponse> transactions = new HashSet<>();
}

package com.devhassan.financeapp.bankaccount.repository;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    Optional<BankAccount> findByAccountNumber(String accountNumber);
    List<BankAccount> findBankAccountsByUser_Id(UUID userId);
}

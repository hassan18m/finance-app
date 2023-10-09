package com.devhassan.financeapp.bankaccount.repository;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {
}

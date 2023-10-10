package com.devhassan.financeapp.transaction.repository;

import com.devhassan.financeapp.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}

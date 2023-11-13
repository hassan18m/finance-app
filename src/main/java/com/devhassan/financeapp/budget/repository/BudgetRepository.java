package com.devhassan.financeapp.budget.repository;

import com.devhassan.financeapp.budget.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}

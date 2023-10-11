package com.devhassan.financeapp.expensecategory.repository;

import com.devhassan.financeapp.expensecategory.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}

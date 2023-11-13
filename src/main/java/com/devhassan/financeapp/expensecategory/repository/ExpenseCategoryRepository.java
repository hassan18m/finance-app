package com.devhassan.financeapp.expensecategory.repository;

import com.devhassan.financeapp.expensecategory.entity.ExpenseCategory;
import com.devhassan.financeapp.expensecategory.entity.enums.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    Optional<ExpenseCategory> findByCategoryName(CategoryName categoryName);
}

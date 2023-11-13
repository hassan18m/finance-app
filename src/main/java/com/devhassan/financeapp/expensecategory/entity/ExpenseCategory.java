package com.devhassan.financeapp.expensecategory.entity;

import com.devhassan.financeapp.budget.entity.Budget;
import com.devhassan.financeapp.expensecategory.entity.enums.CategoryName;
import com.devhassan.financeapp.transaction.entity.Transaction;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "expense_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "category_name", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CategoryName categoryName;

    @OneToMany(mappedBy = "expenseCategory")
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToMany(mappedBy = "expenseCategories")
    private Set<Budget> budgets = new HashSet<>();

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ExpenseCategory that = (ExpenseCategory) object;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

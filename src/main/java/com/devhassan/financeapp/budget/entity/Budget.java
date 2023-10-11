package com.devhassan.financeapp.budget.entity;

import com.devhassan.financeapp.expensecategory.entity.ExpenseCategory;
import com.devhassan.financeapp.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "budget")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToMany
    @JoinTable(
            name = "budget_expense_categories",
            joinColumns = @JoinColumn(name = "budget_id"),
            inverseJoinColumns = @JoinColumn(name = "expense_category_id")
    )
    Set<ExpenseCategory> expenseCategories = new HashSet<>();
}

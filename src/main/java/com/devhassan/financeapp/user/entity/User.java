package com.devhassan.financeapp.user.entity;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.budget.entity.Budget;
import com.devhassan.financeapp.financialInsight.entity.FinancialInsight;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.*;

@Entity(name = "user")
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    @NotBlank
    @Email
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Budget> budgets = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<FinancialInsight> financialInsights = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id != null && id.equals(user.id);
    }
}

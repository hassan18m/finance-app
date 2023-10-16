package com.devhassan.financeapp.user.entity;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.budget.entity.Budget;
import com.devhassan.financeapp.financialInsight.entity.FinancialInsight;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity(name = "user")
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails{
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

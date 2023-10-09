package com.devhassan.financeapp.user.entity;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity(name = "user")
@Table(name = "\"user\"")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "first_name", nullable = false, columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", nullable = false, columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, columnDefinition = "TEXT")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<BankAccount> bankAccounts = new HashSet<>();

    @Override
    public int hashCode() {
        return Objects.hash(id); // Use a unique field like id for hashing
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id != null && id.equals(user.id); // Use a unique field like id for equality
    }
}

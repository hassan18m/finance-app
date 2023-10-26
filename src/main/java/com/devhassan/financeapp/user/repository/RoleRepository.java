package com.devhassan.financeapp.user.repository;

import com.devhassan.financeapp.user.entity.enums.ERole;
import com.devhassan.financeapp.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}

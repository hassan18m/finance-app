package com.devhassan.financeapp.authentication.model;

import com.devhassan.financeapp.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UUID id;
    private String token;
    private Role role;
}

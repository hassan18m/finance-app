package com.devhassan.financeapp.authentication.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}

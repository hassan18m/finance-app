package com.devhassan.financeapp.user.entity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

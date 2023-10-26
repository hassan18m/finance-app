package com.devhassan.financeapp.authentication.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserInfoResponse {
    private UUID id;
    private String email;
    private List<String> roles;
}

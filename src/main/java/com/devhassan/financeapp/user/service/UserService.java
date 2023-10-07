package com.devhassan.financeapp.user.service;

import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;

public interface UserService {
    UserResponse insertUser(UserRequest userRequest);
}

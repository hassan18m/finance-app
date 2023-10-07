package com.devhassan.financeapp.user.controller;

import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.exceptions.DuplicateDataException;
import com.devhassan.financeapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@RequestBody UserRequest userRequest) {
        try {
            return ResponseEntity.ok(userService.insertUser(userRequest));
        } catch (DuplicateDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}

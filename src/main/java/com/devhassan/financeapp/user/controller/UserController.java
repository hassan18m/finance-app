package com.devhassan.financeapp.user.controller;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.budget.entity.model.BudgetRequest;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.exceptions.DuplicateDataException;
import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.user.entity.model.UserResponse;
import com.devhassan.financeapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            return ResponseEntity.ok(userService.findByEmail(email));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(userService.findById(id));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> insertUser(@RequestBody UserRequest userRequest) {
        try {
            return ResponseEntity.ok(userService.insertUser(userRequest));
        } catch (DuplicateDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("{userId}/add-bank-account")
    public ResponseEntity<?> addBankAccountToUser(@PathVariable UUID userId, @RequestBody BankAccountRequest bankAccountRequest) {
        try {
            return ResponseEntity.ok(userService.addBankAccount(userId, bankAccountRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("{userId}/add-budget")
    public ResponseEntity<?> setBudgetToUser(@PathVariable UUID userId, @RequestBody BudgetRequest budgetRequest) {
        try {
            return ResponseEntity.ok(userService.setBudget(userId, budgetRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

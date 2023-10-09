package com.devhassan.financeapp.user.controller;

import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.exceptions.DuplicateDataException;
import com.devhassan.financeapp.user.exceptions.NotFoundException;
import com.devhassan.financeapp.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<?> findByEmail(@RequestParam String email) {
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

    @PostMapping("/{id}")
    public ResponseEntity<?> addBankAccountToUser(@PathVariable UUID id, @RequestBody BankAccountRequest bankAccountRequest) {
        try {
            return ResponseEntity.ok(userService.addBankAccount(id, bankAccountRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

package com.devhassan.financeapp.budget.controller;

import com.devhassan.financeapp.budget.entity.model.UpdateBudgetRequest;
import com.devhassan.financeapp.budget.service.BudgetService;
import com.devhassan.financeapp.exceptions.InvalidDataException;
import com.devhassan.financeapp.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserBudgets(@PathVariable UUID userId) {
        try {
            return ResponseEntity.ok(budgetService.getUserBudgets(userId));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{budgetId}/update")
    public ResponseEntity<?> updateBudget(@PathVariable Long budgetId,
                                          @RequestBody UpdateBudgetRequest budgetRequest) {
        try {
            return ResponseEntity.ok(budgetService.updateBudget(budgetId, budgetRequest));
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (InvalidDataException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<?> deleteBudget(@PathVariable Long budgetId) {
        try {
            budgetService.removeBudget(budgetId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

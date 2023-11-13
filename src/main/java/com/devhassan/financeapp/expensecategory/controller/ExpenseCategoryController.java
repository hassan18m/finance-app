package com.devhassan.financeapp.expensecategory.controller;

import com.devhassan.financeapp.expensecategory.entity.enums.CategoryName;
import com.devhassan.financeapp.expensecategory.service.ExpenseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expense-categories")
@RequiredArgsConstructor
public class ExpenseCategoryController {
    private final ExpenseCategoryService expenseCategoryService;

    @GetMapping
    public ResponseEntity<List<CategoryName>> getAllExpenseCategories() {
        return ResponseEntity.ok(expenseCategoryService.findAll());
    }
}

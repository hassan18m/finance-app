package com.devhassan.financeapp.expensecategory.service;

import com.devhassan.financeapp.expensecategory.entity.enums.CategoryName;

import java.util.List;

public interface ExpenseCategoryService {
    List<CategoryName> findAll();
}

package com.devhassan.financeapp.expensecategory.service;

import com.devhassan.financeapp.expensecategory.entity.enums.CategoryName;
import com.devhassan.financeapp.expensecategory.entity.model.ExpenseCategoryResponse;
import com.devhassan.financeapp.expensecategory.repository.ExpenseCategoryRepository;
import com.devhassan.financeapp.globalhelper.MapEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {
    private final ExpenseCategoryRepository expenseCategoryRepository;

    @Override
    public List<CategoryName> findAll() {
        return expenseCategoryRepository.findAll()
                .stream()
                .map(MapEntity::budgetExpenseCategoryEntityToResponse)
                .map(ExpenseCategoryResponse::getCategoryName)
                .collect(Collectors.toList());
    }
}

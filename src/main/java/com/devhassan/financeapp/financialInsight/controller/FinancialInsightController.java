package com.devhassan.financeapp.financialInsight.controller;

import com.devhassan.financeapp.financialInsight.service.FinancialInsightService;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/insights")
public class FinancialInsightController {
    private final FinancialInsightService financialInsightService;
    private final UserRepository userRepository;

    @Autowired
    public FinancialInsightController(FinancialInsightService financialInsightService,
                                      UserRepository userRepository) {
        this.financialInsightService = financialInsightService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> generateAndStoreInsight(@PathVariable UUID userId) {
        try {
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
            User user = userOptional.get();

            financialInsightService.generateAndSaveInsights(user);

            return new ResponseEntity<>("Insight generated and stored successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error generating and storing insight", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

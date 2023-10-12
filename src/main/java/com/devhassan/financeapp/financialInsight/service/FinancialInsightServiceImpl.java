package com.devhassan.financeapp.financialInsight.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.financialInsight.entity.FinancialInsight;
import com.devhassan.financeapp.financialInsight.repository.FinancialInsightRepository;
import com.devhassan.financeapp.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FinancialInsightServiceImpl implements FinancialInsightService {
    private final FinancialInsightRepository financialInsightRepository;

    @Autowired
    public FinancialInsightServiceImpl(FinancialInsightRepository financialInsightRepository) {
        this.financialInsightRepository = financialInsightRepository;
    }

    public void generateAndSaveInsights(User user) {
        // Implement logic to analyze user's financial behavior and generate insights
        String insightMessage = analyzeAndGenerateInsight(user);

        // Save the generated insight
        FinancialInsight insight = FinancialInsight.builder()
                .insightType(insightMessage)
                .user(user)
                .build();

        financialInsightRepository.save(insight);
    }

    private String analyzeAndGenerateInsight(User user) {
        // Implement your analysis logic here
        // This could involve querying transactions, budgets, or any other relevant data
        // Generate a meaningful message based on the analysis
        // Return the generated message

        List<BankAccount> bankAccountsOwnedByUser = user.getBankAccounts()
                .stream()
                .toList();
        double totalBudgetOwnedByUser = bankAccountsOwnedByUser
                .stream()
                .mapToDouble(bankAccount -> bankAccount.getBalance().doubleValue())
                .sum();

        if (totalBudgetOwnedByUser < 0) {
            return "Your balance is at high risk (" + totalBudgetOwnedByUser + ").";
        }
        return "Your balance looks right!";
    }
}

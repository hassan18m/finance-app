package com.devhassan.financeapp.financialInsight.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.financialInsight.entity.FinancialInsight;
import com.devhassan.financeapp.financialInsight.repository.FinancialInsightRepository;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@Service
public class FinancialInsightServiceImpl implements FinancialInsightService {
    private final FinancialInsightRepository financialInsightRepository;
    private final UserRepository userRepository;

    @Autowired
    public FinancialInsightServiceImpl(FinancialInsightRepository financialInsightRepository,
                                       UserRepository userRepository) {
        this.financialInsightRepository = financialInsightRepository;
        this.userRepository = userRepository;
    }

    public void generateInsight(User user) {
        List<BankAccount> bankAccountsOwnedByUser = user.getBankAccounts()
                .stream()
                .toList();
        if (!bankAccountsOwnedByUser.isEmpty()) {
            double totalBudgetOwnedByUser = bankAccountsOwnedByUser
                    .stream()
                    .mapToDouble(bankAccount -> bankAccount.getBalance().doubleValue())
                    .sum();
            if (totalBudgetOwnedByUser < 60.00) {
                Set<FinancialInsight> financialInsightsOfUser = user.getFinancialInsights();
                FinancialInsight financialInsight = new FinancialInsight();
                financialInsight.setUser(user);
                financialInsight.setInsightType("");


                financialInsight.setInsightType("Your balance has fallen below 60.00 (" + totalBudgetOwnedByUser + ").");

                financialInsightsOfUser.add(financialInsight);
                financialInsightRepository.save(financialInsight);
                user.setFinancialInsights(financialInsightsOfUser);
                userRepository.save(user);
            }
        }
    }
}

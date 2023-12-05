package com.devhassan.financeapp.authentication;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.globalhelper.Converter;
import com.devhassan.financeapp.transaction.repository.TransactionRepository;
import com.devhassan.financeapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final Statistics statistics;

    public Statistics getStatistics() {
        statistics.setNoOfUsers(noOfUsers());
        statistics.setMoneyInCirculation(totalMoney());
        statistics.setNoOfBankAccounts(totalBankAccounts());
        statistics.setNoOfTransactions(totalNoOfTransactions());
        statistics.setMostUsedBank(mostUsedBank());

        return statistics;
    }

    private int noOfUsers() {
        return userRepository.findAll().size();
    }

    private double totalMoney() {
        return bankAccountRepository.findAll()
                .stream()
                .map(bankAccount -> Converter.convertToRon(bankAccount.getBalance().doubleValue(),
                        bankAccount.getCurrency()))
                .reduce(Double::sum)
                .orElse(0d);
    }

    private int totalBankAccounts() {
        return bankAccountRepository.findAll().size();
    }

    private long totalNoOfTransactions() {
        return transactionRepository.findAll().size();
    }

    private String mostUsedBank() {
        List<String> bankNames = bankAccountRepository.findAll().stream()
                .map(BankAccount::getBankName)
                .toList();

        return bankNames.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }
}



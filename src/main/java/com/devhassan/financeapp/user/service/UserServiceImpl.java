package com.devhassan.financeapp.user.service;

import com.devhassan.financeapp.bankaccount.entity.BankAccount;
import com.devhassan.financeapp.bankaccount.entity.model.BankAccountRequest;
import com.devhassan.financeapp.bankaccount.helper.BankAccountInit;
import com.devhassan.financeapp.bankaccount.repository.BankAccountRepository;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.entity.model.UserRequest;
import com.devhassan.financeapp.user.entity.model.UserResponse;
import com.devhassan.financeapp.user.exceptions.DuplicateDataException;
import com.devhassan.financeapp.user.exceptions.NotFoundException;
import com.devhassan.financeapp.user.helper.MapEntity;
import com.devhassan.financeapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BankAccountRepository bankAccountRepository) {
        this.userRepository = userRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    @Override
    public UserResponse insertUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new DuplicateDataException("Email already used!");
        }

        User user = MapEntity.requestToEntity(userRequest);
        userRepository.save(user);

        return MapEntity.entityToResponse(user);
    }

    @Override
    public UserResponse findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(MapEntity::entityToResponse)
                .orElseThrow(() -> new NotFoundException("Email not found!"));
    }

    @Override
    public UserResponse findById(UUID id) {
        return userRepository.findById(id)
                .map(MapEntity::entityToResponse)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public UserResponse addBankAccount(UUID userId, BankAccountRequest bankAccountRequest) {
        User foundUser = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);

        BankAccount bankAccount = BankAccountInit.initBankAccount(foundUser, bankAccountRequest);

        Set<BankAccount> bankAccountsOwnedByUser = foundUser.getBankAccounts();
        bankAccountsOwnedByUser.add(bankAccount);
        foundUser.setBankAccounts(bankAccountsOwnedByUser);

        bankAccount.setUser(foundUser);
        bankAccountRepository.save(bankAccount);
        userRepository.save(foundUser);


        return MapEntity.entityToResponse(foundUser);
    }
}

package com.devhassan.financeapp.authentication;

import com.devhassan.financeapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {
    private final UserRepository userRepository;

    public int numberOfUsers() {
        return userRepository.findAll().size();
    }
}

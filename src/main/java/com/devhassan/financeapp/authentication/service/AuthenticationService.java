package com.devhassan.financeapp.authentication.service;

import com.devhassan.financeapp.authentication.model.RegisterRequest;
import com.devhassan.financeapp.authentication.model.AuthenticationRequest;
import com.devhassan.financeapp.authentication.model.AuthenticationResponse;
import com.devhassan.financeapp.exceptions.DuplicateDataException;
import com.devhassan.financeapp.securityconfig.JwtService;
import com.devhassan.financeapp.exceptions.NotFoundException;
import com.devhassan.financeapp.user.entity.Role;
import com.devhassan.financeapp.user.entity.User;
import com.devhassan.financeapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new DuplicateDataException("Email already used!");
        }

        var user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {


        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

        var user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(NotFoundException::new);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

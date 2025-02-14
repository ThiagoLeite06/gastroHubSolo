package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.application.exceptions.AuthenticationException;
import com.thiagoleite.GastroHubSolo.domain.entities.AuthenticationResult;
import com.thiagoleite.GastroHubSolo.domain.entities.User;

import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticationUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationUseCaseImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResult authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Invalid credentials"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());
        return new AuthenticationResult(user, token);
    }
}

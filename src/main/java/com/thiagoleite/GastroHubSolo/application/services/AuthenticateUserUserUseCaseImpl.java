package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.domain.entities.User;

import com.thiagoleite.GastroHubSolo.domain.exceptions.AuthenticationException;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticateUserUserUseCaseImpl implements AuthenticateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticateUserUserUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Credenciais inválidas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Credenciais inválidas");
        }

        user.setLastUpdatedAt(new Date());
        return userRepository.save(user);
    }
}

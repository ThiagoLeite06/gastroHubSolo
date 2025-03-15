package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.CreateUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(UserRepository userRepository, UserTypeRepository userTypeRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(User user) {

        if (user.getUserType() != null && user.getUserType().getId() != null) {
            UserType userType = userTypeRepository.findById(user.getUserType().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));
            user.setUserType(userType);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        user.setLastUpdatedAt(new Date());
        return userRepository.save(user);
    }
}

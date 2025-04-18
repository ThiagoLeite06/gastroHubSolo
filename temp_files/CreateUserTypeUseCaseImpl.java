package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.CreateUserTypeUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreateUserTypeUseCaseImpl implements CreateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public CreateUserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserType execute(UserType userType) {
        if (userTypeRepository.existsByName(userType.getName())) {
            throw new RuntimeException("Tipo de usuário já existe");
        }

        userType.setCreatedAt(LocalDateTime.now());
        userType.setLastUpdatedAt(LocalDateTime.now());
        return userTypeRepository.save(userType);
    }
}

package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.UpdateUserTypeUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdateUserTypeUseCaseImpl implements UpdateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public UpdateUserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserType execute(Long id, UserType userType) {
        UserType existingUserType = userTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        if (!existingUserType.getName().equals(userType.getName()) &&
                userTypeRepository.existsByName(userType.getName())) {
            throw new RuntimeException("Já existe um tipo de usuário com este nome");
        }

        existingUserType.setName(userType.getName());
        existingUserType.setLastUpdatedAt(LocalDateTime.now());

        return userTypeRepository.save(existingUserType);
    }
}

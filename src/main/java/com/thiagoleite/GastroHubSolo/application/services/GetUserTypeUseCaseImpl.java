package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.GetUserTypeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserTypeUseCaseImpl implements GetUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public GetUserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserType getById(Long id) {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));
    }

    @Override
    public List<UserType> getAll() {
        return userTypeRepository.findAll();
    }
}

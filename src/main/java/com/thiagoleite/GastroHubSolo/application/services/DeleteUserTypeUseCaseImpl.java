package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.DeleteUserTypeUseCase;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserTypeUseCaseImpl implements DeleteUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public DeleteUserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public void execute(Long id) {
        if (userTypeRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Tipo de usuário não encontrado");
        }
        userTypeRepository.deleteById(id);
    }
}

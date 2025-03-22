package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.core.usecase.GetUserTypeUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUserTypeUseCaseImpl implements GetUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public GetUserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserTypeDomain getById(Long id) {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));
    }

    @Override
    public List<UserTypeDomain> getAll() {
        return userTypeRepository.findAll();
    }
}

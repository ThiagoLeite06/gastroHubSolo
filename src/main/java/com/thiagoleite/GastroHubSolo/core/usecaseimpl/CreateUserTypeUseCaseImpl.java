package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.core.usecase.CreateUserTypeUseCase;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CreateUserTypeUseCaseImpl implements CreateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public CreateUserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserTypeDomain execute(UserTypeDomain userTypeDomain) {
        if (userTypeRepository.existsByName(userTypeDomain.getName())) {
            throw new RuntimeException("Tipo de usuário já existe");
        }

        userTypeDomain.setCreatedAt(new Date());
        userTypeDomain.setLastUpdatedAt(new Date());
        return userTypeRepository.save(userTypeDomain);
    }
}

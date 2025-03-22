package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.core.usecase.UpdateUserTypeUseCase;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UpdateUserTypeUseCaseImpl implements UpdateUserTypeUseCase {
    private final UserTypeRepository userTypeRepository;

    public UpdateUserTypeUseCaseImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public UserTypeDomain execute(Long id, UserTypeDomain userTypeDomain) {
        UserTypeDomain existingUserTypeDomain = userTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado"));

        if (!existingUserTypeDomain.getName().equals(userTypeDomain.getName()) &&
                userTypeRepository.existsByName(userTypeDomain.getName())) {
            throw new RuntimeException("Já existe um tipo de usuário com este nome");
        }

        existingUserTypeDomain.setName(userTypeDomain.getName());
        existingUserTypeDomain.setLastUpdatedAt(new Date());

        return userTypeRepository.save(existingUserTypeDomain);
    }
}

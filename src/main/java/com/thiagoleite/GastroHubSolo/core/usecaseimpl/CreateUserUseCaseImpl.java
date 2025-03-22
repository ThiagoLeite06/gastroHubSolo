package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.core.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.core.usecase.CreateUserUseCase;
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
    public UserDomain execute(UserDomain userDomain) {

        if (userDomain.getUserTypeDomain() != null && userDomain.getUserTypeDomain().getId() != null) {
            UserTypeDomain userTypeDomain = userTypeRepository.findById(userDomain.getUserTypeDomain().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));
            userDomain.setUserTypeDomain(userTypeDomain);
        }

        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        userDomain.setRole("USER");
        userDomain.setLastUpdatedAt(new Date());
        return userRepository.save(userDomain);
    }
}

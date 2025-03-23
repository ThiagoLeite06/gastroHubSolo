package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UpdateUserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.UpdateUserUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserTypeRepository userTypeRepository;
    private final UserMapper userMapper;

    public UpdateUserUseCaseImpl(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 UserTypeRepository userTypeRepository,
                                 UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userTypeRepository = userTypeRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserOutput execute(Long id, UpdateUserInput input) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setName(input.getName());
        existingUser.setEmail(input.getEmail());
        existingUser.setAddress(input.getAddress());

        if (input.getPassword() != null && !input.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(input.getPassword()));
        }

        if (input.getUserTypeId() != null) {
            UserType userType = userTypeRepository.findById(input.getUserTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));
            existingUser.setUserType(userType);
        }

        existingUser.setLastUpdatedAt();

        User updatedUser = userRepository.save(existingUser);
        return userMapper.toOutput(updatedUser);
    }
}

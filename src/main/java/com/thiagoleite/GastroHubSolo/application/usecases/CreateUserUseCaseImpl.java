package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserOutput;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.CreateUserUseCase;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public CreateUserUseCaseImpl(UserRepository userRepository,
                                 UserTypeRepository userTypeRepository,
                                 PasswordEncoder passwordEncoder,
                                 UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserOutput execute(UserInput userInput) {

        if (userRepository.existsByEmail(userInput.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }

        User user = new User();
        user.setName(userInput.getName());
        user.setEmail(userInput.getEmail());
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        user.setRole("USER");
        user.setLastUpdatedAt();
        user.setAddress(userInput.getAddress());

        if (userInput.getUserTypeId() != null) {
            UserType userType = userTypeRepository.findById(userInput.getUserTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));
            user.setUserType(userType);
        }

        User savedUser = userRepository.save(user);
        return userMapper.toOutput(savedUser);
    }
}

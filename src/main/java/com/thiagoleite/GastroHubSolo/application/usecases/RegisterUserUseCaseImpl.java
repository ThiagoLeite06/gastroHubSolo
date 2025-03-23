package com.thiagoleite.GastroHubSolo.application.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthOutput;
import com.thiagoleite.GastroHubSolo.application.dtos.UserInput;
import com.thiagoleite.GastroHubSolo.application.dtos.AuthInput;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.application.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.domain.usecases.AuthenticateUserUseCase;
import com.thiagoleite.GastroHubSolo.domain.usecases.RegisterUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserUseCaseImpl implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticateUserUseCase authenticateUserUseCase;

    public RegisterUserUseCaseImpl(UserRepository userRepository,
                                   UserTypeRepository userTypeRepository,
                                   PasswordEncoder passwordEncoder,
                                   AuthenticateUserUseCase authenticateUserUseCase) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    public AuthOutput execute(UserInput userInput) {

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

        userRepository.save(user);

        // Autenticar o usuário usando o caso de uso apropriado
        AuthInput authInput = new AuthInput();
        authInput.setEmail(userInput.getEmail());
        authInput.setPassword(userInput.getPassword());

        return authenticateUserUseCase.execute(authInput);
    }

}
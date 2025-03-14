package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public RegisterUserUseCase(UserRepository userRepository,
                               UserTypeRepository userTypeRepository,
                               PasswordEncoder passwordEncoder,
                               AuthenticationManager authenticationManager,
                               JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public AuthResponseDTO execute(CreateUserDTO createUserDTO) {

        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }

        UserType userType = userTypeRepository.findById(createUserDTO.getUserTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));


        User user = new User();
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole("USER");
        user.setLastUpdatedAt(new Date());
        user.setAddress(createUserDTO.getAddress());
        user.setUserType(userType);

        User savedUser = userRepository.save(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createUserDTO.getEmail(),
                        createUserDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.generateToken(authentication);

        return AuthResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }
}
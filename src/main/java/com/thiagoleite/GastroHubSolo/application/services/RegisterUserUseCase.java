package com.thiagoleite.GastroHubSolo.application.services;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public RegisterUserUseCase(UserRepository userRepository,
                               PasswordEncoder passwordEncoder,
                               AuthenticationManager authenticationManager,
                               JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    public AuthResponseDTO execute(CreateUserDTO createUserDTO) {
        // Verificar se email já existe
        if (userRepository.existsByEmail(createUserDTO.getEmail())) {
            throw new RuntimeException("Email já está em uso");
        }

        // Criar usuário
        User user = new User();
        user.setName(createUserDTO.getName());
        user.setEmail(createUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        user.setRole("USER");
        user.setLastUpdatedAt(new Date());
        user.setAddress(createUserDTO.getAddress());

        User savedUser = userRepository.save(user);

        // Autenticar o novo usuário
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        createUserDTO.getEmail(),
                        createUserDTO.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Gerar token
        String token = tokenProvider.generateToken(authentication);

        // Retornar resposta
        return AuthResponseDTO.builder()
                .token(token)
                .type("Bearer")
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .build();
    }
}
package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.core.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.dataprovider.security.JwtTokenProvider;
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

        UserDomain userDomain = new UserDomain();
        userDomain.setName(createUserDTO.getName());
        userDomain.setEmail(createUserDTO.getEmail());
        userDomain.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));
        userDomain.setRole("USER");
        userDomain.setLastUpdatedAt(new Date());
        userDomain.setAddress(createUserDTO.getAddress());

        if (createUserDTO.getUserTypeId() != null) {
            UserTypeDomain userTypeDomain = userTypeRepository.findById(createUserDTO.getUserTypeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuário não encontrado"));
            userDomain.setUserTypeDomain(userTypeDomain);
        }

        UserDomain savedUserDomain = userRepository.save(userDomain);

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
                .id(savedUserDomain.getId())
                .name(savedUserDomain.getName())
                .email(savedUserDomain.getEmail())
                .build();
    }
}
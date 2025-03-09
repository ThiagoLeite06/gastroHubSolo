package com.thiagoleite.GastroHubSolo.domain.usecases;

import com.thiagoleite.GastroHubSolo.application.dtos.AuthResponseDTO;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;


public interface AuthenticateUserUseCase {
    AuthResponseDTO execute(AuthenticationManager authenticationManager,
                            UserRepository userRepository,
                            JwtTokenProvider tokenProvider);
}

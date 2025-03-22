package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.core.exceptions.AuthenticationException;
import com.thiagoleite.GastroHubSolo.core.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.core.usecase.ChangePasswordUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class ChangePasswordUseCaseImpl implements ChangePasswordUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ChangePasswordUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(Long userId, String currentPassword, String newPassword) throws AuthenticationException {
        // Buscar usuário pelo ID
        UserDomain userDomain = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        // Verificar senha atual
        if (!passwordEncoder.matches(currentPassword, userDomain.getPassword())) {
            throw new AuthenticationException("Senha atual incorreta");
        }

        // Atualizar para nova senha
        userDomain.setPassword(passwordEncoder.encode(newPassword));
        userDomain.setLastUpdatedAt(new Date());

        userRepository.save(userDomain);
    }
}

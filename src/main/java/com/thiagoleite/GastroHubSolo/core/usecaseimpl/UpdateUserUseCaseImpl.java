package com.thiagoleite.GastroHubSolo.core.usecaseimpl;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.core.usecase.UpdateUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateUserUseCaseImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDomain execute(Long id, UserDomain userDomain) {
        UserDomain existingUserDomain = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUserDomain.setName(userDomain.getName());
        existingUserDomain.setEmail(userDomain.getEmail());

        // Se uma nova senha for fornecida
        if (userDomain.getPassword() != null && !userDomain.getPassword().isEmpty()) {
            existingUserDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        }
        // Se não, mantém a senha atual
        existingUserDomain.setLastUpdatedAt(new Date());
        existingUserDomain.setAddress(userDomain.getAddress());

        return userRepository.save(existingUserDomain);
    }
}

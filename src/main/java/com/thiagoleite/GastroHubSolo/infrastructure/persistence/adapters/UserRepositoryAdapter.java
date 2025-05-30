package com.thiagoleite.GastroHubSolo.infrastructure.persistence.adapters;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserJpaEntity;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    @Override
    public User save(User user) {
        UserJpaEntity userJpaEntity = mapToJpaEntity(user);
        UserJpaEntity savedEntity = userJpaRepository.save(userJpaEntity);
        return mapToDomainEntity(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(this::mapToDomainEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email)
                .map(this::mapToDomainEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    private UserJpaEntity mapToJpaEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setRole(user.getRole());
        entity.setLastUpdatedAt(user.getLastUpdatedAt());
        entity.setAddress(user.getAddress());
//        entity.setUserType(user.getUserType());
        return entity;
    }

    private User mapToDomainEntity(UserJpaEntity jpaEntity) {
        return User.builder()
                .id(jpaEntity.getId())
                .name(jpaEntity.getName())
                .email(jpaEntity.getEmail())
                .password(jpaEntity.getPassword())
                .role(jpaEntity.getRole())
                .lastUpdatedAt(jpaEntity.getLastUpdatedAt())
                .address(jpaEntity.getAddress())
//                .userType(jpaEntity.getUserType())
                .build();
    }
}

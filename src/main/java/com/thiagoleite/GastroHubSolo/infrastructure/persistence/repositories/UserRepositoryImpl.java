package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;

import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = toEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(userEntity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(this::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        return entity;
    }

    private User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword()
        );
    }
}

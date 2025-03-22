package com.thiagoleite.GastroHubSolo.dataprovider.persistence.repositories;

import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserRepository;
import com.thiagoleite.GastroHubSolo.dataprovider.mappers.UserMapper;
import com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities.UserEntity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JpaUserRepository userRepository, UserMapper userMapper) {
        this.jpaUserRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDomain save(UserDomain userDomain) {
        UserEntity entity = userMapper.toEntity(userDomain);
        UserEntity savedEntity = jpaUserRepository.save(entity);
        return userMapper.toUser(savedEntity);
    }

    @Override
    public Optional<UserDomain> findById(Long id) {
        return jpaUserRepository.findById(id)
                .map(userMapper::toUser);
    }

    @Override
    public List<UserDomain> findAll() {
        return jpaUserRepository.findAll().stream()
                .map(userMapper::toUser)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaUserRepository.deleteById(id);
    }

    @Override
    public Optional<UserDomain> findByEmail(String email) {
       return jpaUserRepository.findByEmail(email)
               .map(userMapper::toUser);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }
}

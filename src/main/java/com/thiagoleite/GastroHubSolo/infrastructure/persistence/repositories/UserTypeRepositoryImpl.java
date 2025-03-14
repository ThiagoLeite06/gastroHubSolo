package com.thiagoleite.GastroHubSolo.infrastructure.persistence.repositories;

import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.mappers.UserTypeMapper;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserTypeEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserTypeRepositoryImpl implements UserTypeRepository {
    private final JpaUserTypeRepository jpaUserTypeRepository;
    private final UserTypeMapper userTypeMapper;

    public UserTypeRepositoryImpl(JpaUserTypeRepository jpaUserTypeRepository, UserTypeMapper userTypeMapper) {
        this.jpaUserTypeRepository = jpaUserTypeRepository;
        this.userTypeMapper = userTypeMapper;
    }

    @Override
    public UserType save(UserType userType) {
        UserTypeEntity entity = userTypeMapper.toEntity(userType);
        UserTypeEntity savedEntity = jpaUserTypeRepository.save(entity);
        return userTypeMapper.toUserType(savedEntity);

    }

    @Override
    public Optional<UserType> findById(Long id) {
        return jpaUserTypeRepository.findById(id)
                .map(userTypeMapper::toUserType);
    }

    @Override
    public List<UserType> findAll() {
        return jpaUserTypeRepository.findAll().stream()
                .map(userTypeMapper::toUserType)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaUserTypeRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return jpaUserTypeRepository.existsByName(name);
    }
}

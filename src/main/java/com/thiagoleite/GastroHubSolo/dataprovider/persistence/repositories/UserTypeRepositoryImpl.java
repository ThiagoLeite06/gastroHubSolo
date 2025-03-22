package com.thiagoleite.GastroHubSolo.dataprovider.persistence.repositories;

import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.dataprovider.mappers.UserTypeMapper;
import com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities.UserTypeEntity;
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
    public UserTypeDomain save(UserTypeDomain userTypeDomain) {
        UserTypeEntity entity = userTypeMapper.toEntity(userTypeDomain);
        UserTypeEntity savedEntity = jpaUserTypeRepository.save(entity);
        return userTypeMapper.toUserType(savedEntity);

    }

    @Override
    public Optional<UserTypeDomain> findById(Long id) {
        return jpaUserTypeRepository.findById(id)
                .map(userTypeMapper::toUserType);
    }

    @Override
    public List<UserTypeDomain> findAll() {
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

package com.thiagoleite.GastroHubSolo.infrastructure.mappers;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.UserResponseDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.domain.exceptions.ResourceNotFoundException;
import com.thiagoleite.GastroHubSolo.domain.repositories.UserTypeRepository;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserEntity;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Domain
    public User toUser(CreateUserDTO dto) {
        User user = modelMapper.map(dto, User.class);
        return user;
    }

    // Domain -> DTO
    public UserResponseDTO toResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    // Domain -> Entity
    public UserEntity toEntity(User user) {
        UserEntity entity = modelMapper.map(user, UserEntity.class);

        if (user.getUserType() != null) {
            UserTypeEntity userTypeEntity = new UserTypeEntity();
            userTypeEntity.setId(user.getUserType().getId());
            entity.setUserType(userTypeEntity);
        }

        return entity;
    }

    // Entity -> Domain
    public User toUser(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }
}

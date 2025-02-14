package com.thiagoleite.GastroHubSolo.infrastructure.mappers;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.UserResponseDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.User;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = new ModelMapper();
    }

    // DTO -> Domain
    public User toUser(CreateUserDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    // Domain -> DTO
    public UserResponseDTO toResponseDTO(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    // Domain -> Entity
    public UserEntity toEntity(User user) {
        return modelMapper.map(user, UserEntity.class);
    }

    // Entity -> Domain
    public User toUser(UserEntity entity) {
        return modelMapper.map(entity, User.class);
    }
}

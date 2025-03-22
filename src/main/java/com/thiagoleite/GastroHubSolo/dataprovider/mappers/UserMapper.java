package com.thiagoleite.GastroHubSolo.dataprovider.mappers;

import com.thiagoleite.GastroHubSolo.entrypoint.dtos.CreateUserDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.UserResponseDTO;
import com.thiagoleite.GastroHubSolo.core.domain.UserDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities.UserEntity;
import com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities.UserTypeEntity;
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
    public UserDomain toUser(CreateUserDTO dto) {
        UserDomain userDomain = modelMapper.map(dto, UserDomain.class);
        return userDomain;
    }

    // Domain -> DTO
    public UserResponseDTO toResponseDTO(UserDomain userDomain) {
        return modelMapper.map(userDomain, UserResponseDTO.class);
    }

    // Domain -> Entity
    public UserEntity toEntity(UserDomain userDomain) {
        UserEntity entity = modelMapper.map(userDomain, UserEntity.class);

        if (userDomain.getUserTypeDomain() != null) {
            UserTypeEntity userTypeEntity = new UserTypeEntity();
            userTypeEntity.setId(userDomain.getUserTypeDomain().getId());
            entity.setUserType(userTypeEntity);
        }

        return entity;
    }

    // Entity -> Domain
    public UserDomain toUser(UserEntity entity) {
        return modelMapper.map(entity, UserDomain.class);
    }
}

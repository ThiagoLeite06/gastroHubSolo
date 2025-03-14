package com.thiagoleite.GastroHubSolo.infrastructure.mappers;

import com.thiagoleite.GastroHubSolo.application.dtos.CreateUserTypeDTO;
import com.thiagoleite.GastroHubSolo.application.dtos.UserTypeResponseDTO;
import com.thiagoleite.GastroHubSolo.domain.entities.UserType;
import com.thiagoleite.GastroHubSolo.infrastructure.persistence.entities.UserTypeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTypeMapper {
    private final ModelMapper modelMapper;

    @Autowired
    public UserTypeMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // DTO -> Domain
    public UserType toUserType(CreateUserTypeDTO dto) {
        return modelMapper.map(dto, UserType.class);
    }

    // Domain -> DTO
    public UserTypeResponseDTO toResponseDTO(UserType userType) {
        return modelMapper.map(userType, UserTypeResponseDTO.class);
    }

    // Domain -> Entity
    public UserTypeEntity toEntity(UserType userType) {
        return modelMapper.map(userType, UserTypeEntity.class);
    }

    // Entity -> Domain
    public UserType toUserType(UserTypeEntity entity) {
        return modelMapper.map(entity, UserType.class);
    }
}

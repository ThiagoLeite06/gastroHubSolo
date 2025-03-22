package com.thiagoleite.GastroHubSolo.dataprovider.mappers;

import com.thiagoleite.GastroHubSolo.entrypoint.dtos.CreateUserTypeDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.UserTypeResponseDTO;
import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.dataprovider.persistence.entities.UserTypeEntity;
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
    public UserTypeDomain toUserType(CreateUserTypeDTO dto) {
        return modelMapper.map(dto, UserTypeDomain.class);
    }

    // Domain -> DTO
    public UserTypeResponseDTO toResponseDTO(UserTypeDomain userTypeDomain) {
        return modelMapper.map(userTypeDomain, UserTypeResponseDTO.class);
    }

    // Domain -> Entity
    public UserTypeEntity toEntity(UserTypeDomain userTypeDomain) {
        return modelMapper.map(userTypeDomain, UserTypeEntity.class);
    }

    // Entity -> Domain
    public UserTypeDomain toUserType(UserTypeEntity entity) {
        return modelMapper.map(entity, UserTypeDomain.class);
    }
}

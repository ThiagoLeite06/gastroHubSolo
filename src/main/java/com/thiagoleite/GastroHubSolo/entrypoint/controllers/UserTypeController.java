package com.thiagoleite.GastroHubSolo.entrypoint.controllers;

import com.thiagoleite.GastroHubSolo.entrypoint.dtos.CreateUserTypeDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.UpdateUserTypeDTO;
import com.thiagoleite.GastroHubSolo.entrypoint.dtos.UserTypeResponseDTO;
import com.thiagoleite.GastroHubSolo.core.domain.UserTypeDomain;
import com.thiagoleite.GastroHubSolo.core.usecase.CreateUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.core.usecase.DeleteUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.core.usecase.GetUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.core.usecase.UpdateUserTypeUseCase;
import com.thiagoleite.GastroHubSolo.dataprovider.mappers.UserTypeMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-types")
public class UserTypeController {

    @Autowired
    private CreateUserTypeUseCase createUserTypeUseCase;

    @Autowired
    private UpdateUserTypeUseCase updateUserTypeUseCase;

    @Autowired
    private DeleteUserTypeUseCase deleteUserTypeUseCase;

    @Autowired
    private GetUserTypeUseCase getUserTypeUseCase;

    @Autowired
    private UserTypeMapper userTypeMapper;

    @PostMapping
    public ResponseEntity<UserTypeResponseDTO> createUserType(@Valid @RequestBody CreateUserTypeDTO createUserTypeDTO) {
        // Converter DTO para domínio
        UserTypeDomain userTypeDomain = new UserTypeDomain();
        userTypeDomain.setName(createUserTypeDTO.getName());

        // Executar caso de uso
        UserTypeDomain createdUserTypeDomain = createUserTypeUseCase.execute(userTypeDomain);

        // Converter resultado para DTO de resposta
        UserTypeResponseDTO responseDTO = userTypeMapper.toResponseDTO(createdUserTypeDomain);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserTypeResponseDTO> updateUserType(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserTypeDTO updateUserTypeDTO) {

        // Converter DTO para domínio
        UserTypeDomain userTypeDomain = new UserTypeDomain();
        userTypeDomain.setName(updateUserTypeDTO.getName());

        // Executar caso de uso
        UserTypeDomain updatedUserTypeDomain = updateUserTypeUseCase.execute(id, userTypeDomain);

        // Converter resultado para DTO de resposta
        UserTypeResponseDTO responseDTO = userTypeMapper.toResponseDTO(updatedUserTypeDomain);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable Long id) {
        deleteUserTypeUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserTypeResponseDTO> getUserTypeById(@PathVariable Long id) {
        UserTypeDomain userTypeDomain = getUserTypeUseCase.getById(id);
        UserTypeResponseDTO responseDTO = userTypeMapper.toResponseDTO(userTypeDomain);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserTypeResponseDTO>> getAllUserTypes() {
        List<UserTypeDomain> userTypeDomains = getUserTypeUseCase.getAll();
        List<UserTypeResponseDTO> userTypeResponseDTOs = userTypeDomains.stream()
                .map(userTypeMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(userTypeResponseDTOs);
    }
}
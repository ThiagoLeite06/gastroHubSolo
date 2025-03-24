package com.thiagoleite.GastroHubSolo.application.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class CreateRestaurantInput {
    @NotBlank(message = "O nome é obrigatório")
    @Length(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O endereço é obrigatório")
    @Length(max = 200, message = "O endereço não pode ter mais que 200 caracteres")
    private String address;

    @NotBlank(message = "O tipo de cozinha é obrigatório")
    @Length(max = 50, message = "O tipo de cozinha não pode ter mais que 50 caracteres")
    private String cuisineType;

    @NotBlank(message = "O horário de funcionamento é obrigatório")
    @Length(max = 100, message = "O horário de funcionamento não pode ter mais que 100 caracteres")
    private String operatingHours;

    @NotNull(message = "O ID do dono do restaurante é obrigatório")
    private Long ownerId;
}